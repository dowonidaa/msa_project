package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.client.ProductClient;
import com.msa.eureka.cilent.order.client.ResponseProduct;
import com.msa.eureka.cilent.order.dto.RequestOrder;
import com.msa.eureka.cilent.order.dto.ResponseOrder;
import com.msa.eureka.cilent.order.entity.Order;
import com.msa.eureka.cilent.order.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public List<ResponseOrder> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderToResponseOrder(orders);

    }

    @Transactional
    public void createOrder(RequestOrder request, String username) {
        for (Long productId : request.getOrderItems()) {
            ResponseProduct product = productClient.getProductById(productId).getBody();

            if (product.getProductQuantity() < 1) {
                throw new IllegalArgumentException("상품: " + productId + " 수량이 0 입니다.");
            }
        }
        for (Long productId : request.getOrderItems()) {
            productClient.reduceQuantity(productId, 1);
        }
        Order order = new Order(request.getOrderItems(), OrderStatus.CREATED, username);
        orderRepository.save(order);
    }


    public ResponseOrder getOrder(Long orderId, String username) {
        Order order = orderRepository.findById(orderId).filter(o -> !o.getIsDelete())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문 내역입니다."));
        if (!order.getCreateBy().equals(username)){
            throw new IllegalArgumentException("해당 주문에 대한 권한이 없습니다.");
        }
        return new ResponseOrder(order);
    }

    @Transactional
    public ResponseOrder updateOrder(Long orderId, RequestOrder request, String username) {
        Order order = orderRepository.findById(orderId).filter(o -> !o.getIsDelete())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문 내역입니다."));
        order.update(request, username);
        return new ResponseOrder(order);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).filter(o -> !o.getIsDelete())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문 내역입니다."));
        order.delete(true);
    }



    // 임시
    private List<ResponseOrder> orderToResponseOrder(List<Order> orders) {
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            ResponseOrder responseOrder = new ResponseOrder(order);
            responseOrders.add(responseOrder);
        }
        return responseOrders;
    }
}

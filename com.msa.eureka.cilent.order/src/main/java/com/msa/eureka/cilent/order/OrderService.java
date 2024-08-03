package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.client.ProductClient;
import com.msa.eureka.cilent.order.client.ResponseProduct;
import com.msa.eureka.cilent.order.dto.RequestOrder;
import com.msa.eureka.cilent.order.dto.ResponseOrder;
import com.msa.eureka.cilent.order.entity.Order;
import com.msa.eureka.cilent.order.entity.OrderItem;
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
    public void addOrder(RequestOrder request) {
        for (Long productId : request.getOrderItems()) {
            ResponseProduct product = productClient.getProductById(productId).getBody();
            if (product.getQuantity() < 1) {
                throw new IllegalArgumentException("상품 수량이 0 입니다.");
            }
            productClient.reduceQuantity();


        }
        productClient.getProductById()
        orderRepository.save(order);
    }















    private List<ResponseOrder> orderToResponseOrder(List<Order> orders) {
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Order order : orders) {
            ResponseOrder responseOrder = new ResponseOrder(order);
            responseOrders.add(responseOrder);
        }
        return responseOrders;
    }
}

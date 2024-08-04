package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.dto.RequestOrder;
import com.msa.eureka.cilent.order.dto.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ResponseOrder>> getOrders() {
        List<ResponseOrder> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody RequestOrder request,
                                         @RequestHeader("X-User-Id")String username) {
        log.info("request = {}", request.getOrderItems());
        orderService.createOrder(request, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId,
                                      @RequestHeader("X-User-Id")String username) {
        ResponseOrder responseOrder = orderService.getOrder(orderId, username);
        return ResponseEntity.ok(responseOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseOrder> updateOrder(@PathVariable Long orderId,
                                                     @RequestBody RequestOrder request,
                                                     @RequestHeader("X-User-Id") String username) {
        ResponseOrder responseOrder = orderService.updateOrder(orderId, request, username);
        return ResponseEntity.ok(responseOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

}



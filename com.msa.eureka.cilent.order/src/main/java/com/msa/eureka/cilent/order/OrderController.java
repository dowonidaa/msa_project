package com.msa.eureka.cilent.order;

import com.msa.eureka.cilent.order.dto.RequestOrder;
import com.msa.eureka.cilent.order.dto.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ResponseOrder>> getOrders() {
        List<ResponseOrder> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody RequestOrder request,
                                         @RequestHeader("X-User_Id")Long userId) {
        orderService.createOrder(request, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId,
                                      @RequestHeader("X-User_Id")Long userId) {
        ResponseOrder responseOrder = orderService.getOrder(orderId, userId);
        return ResponseEntity.ok(responseOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseOrder> updateOrder(@PathVariable Long orderId,
                                                     @RequestBody RequestOrder request,
                                                     @RequestHeader("X-User_Id") Long userId) {
        ResponseOrder responseOrder = orderService.updateOrder(orderId, request, userId);
        return ResponseEntity.ok(responseOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

}



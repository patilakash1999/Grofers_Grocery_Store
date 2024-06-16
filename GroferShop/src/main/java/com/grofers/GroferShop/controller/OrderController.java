package com.grofers.GroferShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grofers.GroferShop.dto.OrderRequestDto;
import com.grofers.GroferShop.entity.Order;
import com.grofers.GroferShop.service.OrderService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId, @RequestBody List<Long> productIds) {
        Order order = orderService.placeOrder(userId, productIds, new Date());
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}/products")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long orderId, @RequestBody List<Long> productIds) {
        Order order = orderService.addProductToOrder(orderId, productIds);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequest) {
        Order order = orderService.updateOrder(orderId, orderRequest.getDeliveryDate());
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
}

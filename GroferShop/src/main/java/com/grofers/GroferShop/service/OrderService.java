package com.grofers.GroferShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grofers.GroferShop.entity.Order;
import com.grofers.GroferShop.entity.Product;
import com.grofers.GroferShop.entity.User;
import com.grofers.GroferShop.repository.OrderRepository;
import com.grofers.GroferShop.repository.ProductRepository;
import com.grofers.GroferShop.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(Long userId, List<Long> productIds, Date deliveryDate) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new RuntimeException("Products not found");
        }

        Order order = new Order();
        order.setUser(userOpt.get());
        order.setProducts(products);
        order.setOrderDate(new Date());
        order.setDeliveryDate(deliveryDate);
        order.setTotalAmount(products.stream().mapToDouble(Product::getPrice).sum());

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserUserId(userId);
    }

    public Order addProductToOrder(Long orderId, List<Long> productIds) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        List<Product> products = productRepository.findAllById(productIds);
        if (products.isEmpty()) {
            throw new RuntimeException("Products not found");
        }

        Order order = orderOpt.get();
        order.getProducts().addAll(products);
        order.setTotalAmount(order.getTotalAmount() + products.stream().mapToDouble(Product::getPrice).sum());

        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Date deliveryDate) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            throw new RuntimeException("Order not found");
        }

        Order order = orderOpt.get();
        order.setDeliveryDate(deliveryDate);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }
}
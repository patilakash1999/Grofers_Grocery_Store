package com.grofers.GroferShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.GroferShop.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUserId(Long userId);
}

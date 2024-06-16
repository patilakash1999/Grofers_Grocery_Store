package com.grofers.GroferShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.GroferShop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

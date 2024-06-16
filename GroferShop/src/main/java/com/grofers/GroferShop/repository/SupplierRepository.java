package com.grofers.GroferShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.GroferShop.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}

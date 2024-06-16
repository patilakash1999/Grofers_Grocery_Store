package com.grofers.GroferShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.GroferShop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
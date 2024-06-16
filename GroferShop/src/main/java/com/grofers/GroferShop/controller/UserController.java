package com.grofers.GroferShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grofers.GroferShop.dto.UserDto;
import com.grofers.GroferShop.entity.User;
import com.grofers.GroferShop.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole("ROLE_USER");

        return ResponseEntity.ok(userService.registerUser(user));
    }
}

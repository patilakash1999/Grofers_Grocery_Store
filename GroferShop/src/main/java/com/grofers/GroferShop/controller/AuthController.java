package com.grofers.GroferShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.grofers.GroferShop.config.JwtTokenProvider;
import com.grofers.GroferShop.dto.UserDto;
import com.grofers.GroferShop.entity.User;
import com.grofers.GroferShop.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) {
        try {
            String email = userDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, userDto.getPassword()));

            User user = userService.findByEmail(email);
            String token = jwtTokenProvider.createToken(email, user.getRole());

            Map<String, String> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email/password supplied");
        }
    }
}

package com.contasys.auth.service.impl;

import com.contasys.auth.dto.AuthResponse;
import com.contasys.auth.dto.LoginRequest;

import com.contasys.auth.service.AuthService;

import com.contasys.modules.users.entity.User;
import com.contasys.modules.users.repository.UserRepository;

import com.contasys.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {

            throw new RuntimeException("Password incorrecto");

        }

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return AuthResponse.builder()
                .token(token)
                .build();

    }

}
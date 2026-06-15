package com.contasys.auth.service;

import com.contasys.auth.dto.AuthResponse;
import com.contasys.auth.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);

}
package com.contasys.modules.users.service;

import com.contasys.modules.users.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();

}
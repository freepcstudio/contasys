package com.contasys.modules.users.service.impl;

import com.contasys.modules.users.dto.UserResponseDto;
import com.contasys.modules.users.mapper.UserMapper;
import com.contasys.modules.users.repository.UserRepository;
import com.contasys.modules.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> findAll() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();

    }

}
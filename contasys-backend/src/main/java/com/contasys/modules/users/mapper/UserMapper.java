package com.contasys.modules.users.mapper;

import com.contasys.modules.users.dto.UserResponseDto;
import com.contasys.modules.users.entity.User;

public class UserMapper {

    public static UserResponseDto toDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();

    }

}
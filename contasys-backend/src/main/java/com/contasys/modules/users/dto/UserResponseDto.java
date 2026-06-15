package com.contasys.modules.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

}
package com.contasys.modules.users.controller;

import com.contasys.modules.users.dto.UserResponseDto;
import com.contasys.modules.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> findAll() {

        return userService.findAll();

    }

}
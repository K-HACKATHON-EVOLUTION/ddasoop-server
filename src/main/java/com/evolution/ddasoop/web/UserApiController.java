package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/users/{userIdx}/main")
    public UserMainResponseDto getMainInfo(@PathVariable Long userIdx){
        return userService.getMainInfo(userIdx);
    }

    @GetMapping("/api/users/{userIdx}")
    public UserResponseDto getUser(@PathVariable Long userIdx){
        return userService.getUser(userIdx);
    }
}

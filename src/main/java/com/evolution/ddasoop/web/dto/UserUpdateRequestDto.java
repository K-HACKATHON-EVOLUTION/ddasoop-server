package com.evolution.ddasoop.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserUpdateRequestDto {
    private String userName;

    public UserUpdateRequestDto(String userName){
        this.userName = userName;
    }
}

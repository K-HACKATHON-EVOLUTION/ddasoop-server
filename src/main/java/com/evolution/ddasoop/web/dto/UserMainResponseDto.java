package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.User;
import lombok.Getter;

@Getter
public class UserMainResponseDto {
    private String userIdx;
    private Double totalCarbon;

    public UserMainResponseDto(User entity){
        this.userIdx = entity.getUserIdx();
        this.totalCarbon = entity.getTotalCarbon();
    }
}

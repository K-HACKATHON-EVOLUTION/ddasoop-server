package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private Long userIdx;
    private String userName;
    private String treeImg;
}

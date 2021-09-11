package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSaveRequestDto {
    private String userIdx;
    private String userName;

    @Builder
    public UserSaveRequestDto(String userIdx, String userName){
        this.userIdx = userIdx;
        this.userName = userName;
    }
}

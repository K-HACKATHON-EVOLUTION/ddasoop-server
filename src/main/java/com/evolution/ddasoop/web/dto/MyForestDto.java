package com.evolution.ddasoop.web.dto;

import lombok.Builder;

public class MyForestDto {
    private Long forestIdx;
    private String forestName;
    private String forestImg;

    @Builder
    public MyForestDto(Long forestIdx, String forestName, String forestImg){
        this.forestIdx = forestIdx;
        this.forestName = forestName;
        this.forestImg = forestImg;
    }


}

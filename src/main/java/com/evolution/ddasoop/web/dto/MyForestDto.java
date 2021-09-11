package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyForestDto {
    private Long forestIdx;
    private String forestName;
    private String forestImg;

    @Builder
    public MyForestDto(Long forestIdx, String forestName, String forestImg){
       this.forestIdx = forestIdx;
       this.forestImg = forestImg;
       this.forestName = forestName;
    }


}

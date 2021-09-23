package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ForestListResponseDto {

    private Long forestIdx;
    private String leader;
    private String forestName;
    private Integer size;
    private String forestImg;
    private boolean deleteFlag;

    /*public ForestListResponseDto(Forest entity){
        this.forestIdx =entity.getForestIdx();
        this.leader = entity.getLeader();
        this.forestName = entity.getForestName();
        this.size = entity.getSize();
        this. forestImg = entity.getForestImg();
        this.deleteFlag = entity.getDeleteFlag();
    }*/

}

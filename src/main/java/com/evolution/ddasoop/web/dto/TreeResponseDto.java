package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Image;
import com.evolution.ddasoop.domain.Tree;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TreeResponseDto {
    private Long treeIdx;
    private Long userIdx;
    private Double treeCarbon;
    private Integer growth;
    private String treeImg;

    public TreeResponseDto(Tree entity){
        this.treeIdx = entity.getTreeIdx();
        this.userIdx = entity.getTreeIdx();
        this.treeCarbon = entity.getTreeCarbon();
        this.growth = entity.getGrowth();
        this.treeImg = entity.getTreeImg().getFilePath()+entity.getTreeImg().getOriginalFileName();
    }
}

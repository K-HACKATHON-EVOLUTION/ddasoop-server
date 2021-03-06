package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Image;
import com.evolution.ddasoop.domain.Tree;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class TreeResponseDto {
    private Long treeIdx;
    private String userIdx;
    private String treeName;
    private Double treeCarbon;
    private Integer growth;
    private String treeImg;

    public TreeResponseDto(Tree entity){
        this.treeIdx = entity.getTreeIdx();
        this.userIdx = entity.getUser().getUserIdx();
        this.treeName = entity.getTreeName();
        this.treeCarbon = entity.getTreeCarbon();
        this.growth = entity.getGrowth();
        this.treeImg = entity.getTreeImg().getFilePath();
    }
}

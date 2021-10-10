package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import lombok.Getter;

@Getter
public class BadgeResponseDto {
    private Long badgeIdx;
    private String userIdx;
    private String treeImg;

    public BadgeResponseDto(Badge entity){
        this.badgeIdx = entity.getBadgeIdx();
        this.userIdx = entity.getUser().getUserIdx();

        if(entity.getTree() != null)
           this.treeImg = entity.getTree().getTreeImg().getFilePath();
    }
}

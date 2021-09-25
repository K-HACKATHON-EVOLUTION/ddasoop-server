package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import lombok.Getter;

@Getter
public class BadgeListResponseDto {
    private Long badgeIdx;
    private String userIdx;
    private String badgeImg;
    private Long treeIdx;

    public BadgeListResponseDto(Badge entity){
        this.badgeIdx = entity.getBadgeIdx();
        this.userIdx = entity.getUser().getUserIdx();
        this.badgeImg = entity.getBadgeImg().getFilePath();
        this.treeIdx = entity.getTree().getTreeIdx();
    }
}

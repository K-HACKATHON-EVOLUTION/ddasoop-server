package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import lombok.Getter;

@Getter
public class BadgeResponseDto {
    private Long badgeIdx;
    private Long userIdx;
    private String treeImg;

    public BadgeResponseDto(Badge entity){
        this.badgeIdx = entity.getBadgeIdx();
        this.userIdx = entity.getUser().getUserIdx();
        this.treeImg = entity.getTree().getTreeImg().getFilePath()+entity.getTree().getTreeImg().getOriginalFileName();
    }
}

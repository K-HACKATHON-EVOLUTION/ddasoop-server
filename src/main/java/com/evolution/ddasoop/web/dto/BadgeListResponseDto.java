package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BadgeListResponseDto {
    private Long badgeIdx;
    private Long userIdx;
    private String badgeImg;
    private Long treeIdx;

    public BadgeListResponseDto(Badge entity){
        this.badgeIdx = entity.getBadgeIdx();
        this.userIdx = entity.getUser().getUserIdx();
        this.badgeImg = entity.getBadgeImg().getFilePath()+entity.getBadgeImg().getOriginalFileName();
        this.treeIdx = entity.getTree().getTreeIdx();
    }
}

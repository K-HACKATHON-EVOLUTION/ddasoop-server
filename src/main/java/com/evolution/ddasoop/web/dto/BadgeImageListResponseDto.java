package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import com.evolution.ddasoop.domain.BadgeImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeImageListResponseDto {
    private Long badgeImgIdx;
    private String filePath;
    private Integer count;

    public BadgeImageListResponseDto(BadgeImage entity){
        this.badgeImgIdx = entity.getBadgeImgIdx();
        this.filePath = entity.getFilePathBlack();
        this.count = 0;
    }

    public BadgeImageListResponseDto(Badge entity){
        this(entity.getBadgeImg());
    }
}

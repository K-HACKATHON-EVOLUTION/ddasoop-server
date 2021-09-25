package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Badge;
import com.evolution.ddasoop.domain.BadgeImage;
import lombok.Getter;

@Getter
public class BadgeImageListResponseDto {
    private Long badgeImgIdx;
    private String filePath;
    private Integer count;

    public BadgeImageListResponseDto(BadgeImage entity){
        this.badgeImgIdx = entity.getBadgeImgIdx();
        this.filePath = entity.getFilePath();
        this.count = 0;
    }

    public BadgeImageListResponseDto(Badge entity){
        this(entity.getBadgeImg());
    }

    public void updateCount(){
        count++;
    }
}

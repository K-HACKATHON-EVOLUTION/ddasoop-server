package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="badge_image")
public class BadgeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeImgIdx;

    @Column
    private String originalFileName;

    @Column
    private String filePath;

    @Column
    private Long fileSize;

    @Builder
    public BadgeImage(String originalFileName, String filePath, Long fileSize){
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}

package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="forest_image")
public class ForestImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ImageIdx;

    @ManyToOne(targetEntity = Forest.class, fetch = FetchType.LAZY)
    @JoinColumn(name="forest_idx")
    private Forest forest;

    @Column
    private String originalFileName;

    @Column
    private String filePath;

    @Column
    private Long fileSize;

    @Builder
    public ForestImage(String originalFileName, Forest forest, String filePath, Long fileSize) {
        this.forest = forest;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }


    public void updatePath(String url){
        this.filePath = url;
    }

}

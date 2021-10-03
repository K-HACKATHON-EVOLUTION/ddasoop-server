package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name= "forest")
public class Forest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forestIdx;

    @Column
    private String forestName;

    @Column
    private String leader;

    @Column
    private Integer size;

    @Column
    private String forestImg;

    @Column
    private Boolean deleteFlag;

    @Column
    private String forestIntro;

    @Builder
    public Forest(Long forestIdx, String forestName, String leader, Integer size, String forestImg, Boolean deleteFlag, String forestIntro) {
        this.forestIdx = forestIdx;
        this.forestName = forestName;
        this.leader = leader;
        this.size = size;
        this.forestImg = forestImg;
        this.deleteFlag = deleteFlag;
        this.forestIntro = forestIntro;
    }

    public void updateName(String forest_name){
        this.forestName = forest_name;
    }

    public void updateIntro(String forest_intro){
        this.forestIntro = forest_intro;
    }

    public void updateDeleteFlag(Boolean flag){
        this.deleteFlag =flag;
    }

    public void updatePhotoUrl(String photoUrl){
        this.forestImg = photoUrl;
    }

}


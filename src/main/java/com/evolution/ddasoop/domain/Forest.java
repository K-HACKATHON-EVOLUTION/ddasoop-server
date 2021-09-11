package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="forest")
public class Forest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forestIdx;

    @Column
    private String forestName;

    @Column
    private Integer leader;

    @Column
    private Integer size;

    @Column
    private String forestImg;

    @Column
    private Boolean deleteFlag;

    @Column
    private String forestIntro;

    public void updateName(String forest_name){
        this.forestName = forest_name;
    }

    public void updateIntro(String forest_intro){
        this.forestIntro = forest_intro;
    }

    public void updateDeleteFlag(Boolean flag){
        this.deleteFlag =flag;
    }

}

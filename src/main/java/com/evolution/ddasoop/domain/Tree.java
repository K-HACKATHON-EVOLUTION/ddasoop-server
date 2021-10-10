package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name= "tree")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treeIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Column
    private String treeName;

    @Column
    private Double treeCarbon;

    @Column
    private Integer growth;

    @OneToOne
    @JoinColumn(name = "tree_img")
    private Image treeImg;

    public static final Double MAX_TREE = 10.0;
    public static final Integer MAX_GROWTH = 5;

    @Builder
    public Tree(User user, String treeName, Double treeCarbon, Integer growth, Image treeImg){
        this.user = user;
        this.treeName = treeName;
        this.treeCarbon = treeCarbon;
        this.growth = growth;
        this.treeImg = treeImg;
    }

    public Double updateTree(Double carbon){
        Double tempCarbon = treeCarbon + carbon;
        Double i = (tempCarbon+0.5);
        Integer tempGrowth = (i.intValue()+1)/2;

        if(tempGrowth > 5){
            //기존 트리 완성
            this.treeCarbon = MAX_TREE;
            this.growth = MAX_GROWTH;
            return tempCarbon-MAX_TREE;
        }
        else if (growth == tempGrowth){
            //기존 트리 성장 안 함
            this.treeCarbon = tempCarbon;
            return -1.0;
        }
        else{
            //기존 트리 성장
            this.growth = tempGrowth;
            this.treeCarbon = tempCarbon;
            return 0.0;
        }
    }

    public void updateTreeName(String newName){
        this.treeName = newName;
    }

    public void updateGrowth(Double carbon){
        Double i = carbon+0.5;
        this.growth = (i.intValue()+1)/2;
    }

    public void updateTreeImg(Image treeImg){
        this.treeImg = treeImg;
    }
}

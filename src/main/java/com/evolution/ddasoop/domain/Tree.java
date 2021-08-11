package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="tree")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treeIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Column
    private Double treeCarbon;

    @Column
    private Integer growth;

    @OneToOne
    @JoinColumn(name = "treeImg")
    private Image treeImg;

    @Builder
    public Tree(User user, Double treeCarbon, Integer growth, Image treeImg){
        this.user = user;
        this.treeCarbon = treeCarbon;
        this.growth = growth;
        this.treeImg = treeImg;
    }
}

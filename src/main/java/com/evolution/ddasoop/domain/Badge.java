package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name= "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @OneToOne
    @JoinColumn(name = "badgeImg")
    private Image badgeImg;

    @ManyToOne(targetEntity = Tree.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "treeIdx")
    private Tree tree;

    @Builder
    public Badge(User user, Image badgeImg, Tree tree){
        this.user = user;
        this.badgeImg = badgeImg;
        this.tree = tree;
    }
}

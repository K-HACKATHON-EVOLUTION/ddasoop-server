package com.evolution.ddasoop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="Badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToOne
    @JoinColumn(name = "badge_img_idx")
    private BadgeImage badgeImg;

    @ManyToOne(targetEntity = Tree.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_idx")
    private Tree tree;

    @Builder
    public Badge(User user, BadgeImage badgeImg, Tree tree){
        this.user = user;
        this.badgeImg = badgeImg;
        this.tree = tree;
    }
}

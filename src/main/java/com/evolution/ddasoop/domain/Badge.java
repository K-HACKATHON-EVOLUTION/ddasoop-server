package com.evolution.ddasoop.domain;

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
    @JoinColumn(name = "user")
    private User user;

    @OneToOne
    @JoinColumn(name = "imageIdx")
    private Image badgeImg;

    @ManyToOne(targetEntity = Tree.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tree")
    private Integer treeIdx;
}

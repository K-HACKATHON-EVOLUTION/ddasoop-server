package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userIdx;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private String userPassword;

    @Column
    private Boolean deleteFlag;

    @Column
    private Integer totalCarbon;

    @ManyToOne(targetEntity = Forest.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "forest")
    private Integer groupIdx;

}

package com.evolution.ddasoop.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@Entity
@Table(name="course")
public class Course {

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseIdx;

    @Column
    private String courseName;

    @Column
    private String courseIntro;

    @Column
    private String theme;


    @Column
    private Timestamp courseDate;

    @Column
    private Boolean deleteFlag;

    @Column
    private Integer distance;

}

package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseIdx;

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
    private Double distance;

    @Column
    private String location;

}

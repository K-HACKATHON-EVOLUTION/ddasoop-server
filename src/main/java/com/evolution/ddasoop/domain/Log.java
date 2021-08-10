package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name="log")
public class Log{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Column
    private Double distance;

    @Column
    private Timestamp startTime;

    @Column
    private Timestamp endTime;

    @Column
    private Double carbon;

    @Column
    private String startLocation;

    @Column
    private String endLocation;
}

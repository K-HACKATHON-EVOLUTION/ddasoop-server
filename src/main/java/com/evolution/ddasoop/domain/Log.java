package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name="log")
public class Log{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logIdx;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @Column
    private Double distance;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Double carbon;

    @Column
    private String startLocation;

    @Column
    private String endLocation;
}

package com.evolution.ddasoop.domain;

import lombok.Builder;
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
    private Long logIdx;

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

    @Column
    private String route;

    private static final Double CARBON_CALCULATION = 0.232;

    @Builder
    public Log(User user, Double distance, LocalDateTime startTime, LocalDateTime endTime, Double carbon, String startLocation, String endLocation, String route){
        this.user = user;
        this.distance = distance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.carbon = carbon;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.route = route;
    }

    public Double calculateCarbon(Double increased){
        carbon = (increased * CARBON_CALCULATION);
        return carbon;
    }
}

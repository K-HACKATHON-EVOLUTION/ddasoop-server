package com.evolution.ddasoop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="forest")
public class Forest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forestIdx;

    @Column
    private String forestName;

    @Column
    private Integer leader;

    @Column
    private Integer size;

    @Column
    private String forestImg;

    @Column
    private Boolean deleteFlag;
}

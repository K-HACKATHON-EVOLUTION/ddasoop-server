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
    private Long groupIdx;

    @Column
    private String groupName;

    @Column
    private Integer leader;

    @Column
    private Integer size;

    @Column
    private String groupImg;

    @Column
    private Boolean deleteFlag;
}

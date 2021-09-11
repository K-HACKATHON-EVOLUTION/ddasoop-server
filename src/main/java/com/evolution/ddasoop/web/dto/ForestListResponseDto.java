package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Forest;

import com.evolution.ddasoop.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class ForestListResponseDto {
    private final List<Forest> forestList;

   /* private Long forestIdx;
    private String forestName;
    private Integer size;
    private String forestImg;
    private double forest_carbon;

    public ForestListResponseDto(Forest forest, User user) {
        this.forestIdx = forest.getForestIdx();
        this.forestName = forest.getForestName();
        this.size = forest.getSize();
        this.forestImg = forest.getForestImg();
        this.forest_carbon = user.getTotalCarbon();
    }*/
}

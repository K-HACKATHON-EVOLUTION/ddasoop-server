package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.*;
import lombok.Getter;

@Getter
public class SearchForestDto {
    private String forest_name;
    private Integer size;
    private String forest_img;
    private Double forest_carbon;

    public SearchForestDto(Forest forest) {
        this.forest_name = forest.getForestName();
        this.size = forest.getSize();
        this.forest_img = forest.getForestImg();
       // this.forest_carbon = user.getTotalCarbon();
    }
}

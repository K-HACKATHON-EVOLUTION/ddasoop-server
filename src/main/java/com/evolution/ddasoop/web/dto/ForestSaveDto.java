package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ForestSaveDto {

    private String forestName;
    private Integer size;
    private String forestIntro;

    public ForestSaveDto(String forestName, Integer size, String forestIntro) {
        this.forestName = forestName;
        this.size = size;
        this.forestIntro = forestIntro;
    }
}

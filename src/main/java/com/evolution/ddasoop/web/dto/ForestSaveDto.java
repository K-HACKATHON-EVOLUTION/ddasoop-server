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
    private String forestImg;
    private String forestIntro;
}

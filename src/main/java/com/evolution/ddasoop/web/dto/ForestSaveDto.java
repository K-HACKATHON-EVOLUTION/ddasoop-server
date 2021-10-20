package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Forest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForestSaveDto {

    private String forestName;
    private Integer size;
    private String forestIntro;

    @Builder
    public ForestSaveDto(Forest forest) {
        this.forestName = forest.getForestName();
        this.size = forest.getSize();
        this.forestIntro = forest.getForestIntro();
    }
}

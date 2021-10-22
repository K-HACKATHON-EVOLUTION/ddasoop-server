package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForestSaveDto {

    private String forestName;
    private String forestImg;
    private String forestIntro;

}

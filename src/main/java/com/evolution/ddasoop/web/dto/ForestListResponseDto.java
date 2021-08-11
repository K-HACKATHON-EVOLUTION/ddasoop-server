package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Forest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.description.field.FieldDescription;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ForestListResponseDto {
    private final List<Forest> forestList;

   /* private Long forestIdx;
    private Integer size;
    private String forestImg;
    private Integer forest_carbon;

    public ForestListResponseDto(Long forestIdx,Integer size, String forestImg, Integer forest_carbon){
        this.forestIdx =forestIdx;
        this.forest_carbon =forest_carbon;
        this.forestImg = forestImg;
        this.size = size;
    }
*/
}

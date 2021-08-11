package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.Forest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class ForestListResponseDto {
    private final List<Forest> forestList;
}

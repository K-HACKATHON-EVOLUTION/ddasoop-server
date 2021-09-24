package com.evolution.ddasoop.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ForestMemberListDto {
    private List member;
    private String leader;
    private double total_trees;

}

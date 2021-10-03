package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberListDto {

    private String user_name;
    private double user_carbon;
    private String user_idx;
}

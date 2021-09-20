package com.evolution.ddasoop.web.dto;


public class ForestMemberListDto {
    private String user_name;
    private double total_carbon;

    public ForestMemberListDto(String user_name, double total_carbon) {
        this.user_name = user_name;
        this.total_carbon = total_carbon;
    }
}

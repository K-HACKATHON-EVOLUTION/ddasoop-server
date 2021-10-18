package com.evolution.ddasoop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberListDto {

    private String user_name;
    private double user_carbon;
    private String user_idx;
    private String user_treeImg;


    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }

        if (obj instanceof MemberListDto) {
            if (this.user_idx == ((MemberListDto) obj).getUser_idx()) {
                return true;
            }
        }
        return false;
    }
}

package com.evolution.ddasoop.web.dto;

import com.evolution.ddasoop.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class UserResponseDto {
    private String userIdx;
    private String userName;
    private List<TreeListResponseDto> trees;
    private Optional<Long> forest;

    @Getter
    @Builder
    public static class TreeListResponseDto{
        private Long treeIdx;
        private String treeImg;
        private String treeName;
        private Double treeCarbon;
    }
}

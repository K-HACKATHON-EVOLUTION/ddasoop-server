package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.TreeService;
import com.evolution.ddasoop.web.dto.TreeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TreeApiController {
    private final TreeService treeService;

    @GetMapping("/api/users/{userIdx}/trees/current")
    public TreeResponseDto getCurrentTree(@PathVariable String userIdx){
        return treeService.getCurrentTree(userIdx);
    }
}

package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.TreeService;
import com.evolution.ddasoop.web.dto.TreeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TreeApiController {
    private final TreeService treeService;

    @GetMapping("/api/users/{userIdx}/trees/current")
    public TreeResponseDto getCurrentTree(@PathVariable String userIdx){
        return treeService.getCurrentTree(userIdx);
    }

    @PatchMapping("/api/users/{userIdx}/trees/current/treeName")
    public ResponseEntity<String> updateCurrentTreeName(@PathVariable String userIdx, @RequestBody String newName){
        try{
            return ResponseEntity.ok().body(treeService.updateTreeName(userIdx, newName));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}

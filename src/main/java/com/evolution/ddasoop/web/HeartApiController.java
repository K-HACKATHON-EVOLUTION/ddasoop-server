package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses/{courseIdx}/users/{userIdx}/hearts")
public class HeartApiController {
    public final HeartService heartService;

    @PatchMapping("")
    public ResponseEntity<Object> changeHeart(@PathVariable Long courseIdx, @PathVariable String userIdx){
        try{
            return ResponseEntity.ok(heartService.changeHeart(courseIdx, userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}

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

    @PostMapping("")
    public ResponseEntity<String> saveHeart(@PathVariable Long courseIdx, @PathVariable String userIdx){
        try{
            return ResponseEntity.ok(heartService.saveHeart(courseIdx, userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteHeart(@PathVariable Long courseIdx, @PathVariable String userIdx){
        try{
            return ResponseEntity.ok(heartService.deleteHeart(courseIdx, userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}

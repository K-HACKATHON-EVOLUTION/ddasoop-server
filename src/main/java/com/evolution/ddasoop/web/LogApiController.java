package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.LogService;
import com.evolution.ddasoop.web.dto.LogListResponseDto;
import com.evolution.ddasoop.web.dto.LogMonthResponseDto;
import com.evolution.ddasoop.web.dto.LogRequestDto;
import com.evolution.ddasoop.web.dto.LogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LogApiController {
    private final LogService logService;

    @GetMapping("/api/users/{userIdx}/logs")
    public ResponseEntity<Object> getMonthlyLog(@PathVariable String userIdx, @RequestParam int month){
        try{
            return ResponseEntity.ok().body(logService.getMonthlyLog(userIdx, Month.of(month)));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PostMapping("/api/users/{userIdx}/logs")
    public ResponseEntity<Object> saveLog(@PathVariable String userIdx, @RequestBody LogRequestDto requestDto){
        try{
            return ResponseEntity.ok().body(logService.saveLog(userIdx, requestDto));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @GetMapping("/api/users/{userIdx}/logLists")
    public ResponseEntity<Object> getLogLists(@PathVariable String userIdx, @RequestParam int month){
        try{
            return ResponseEntity.ok().body(logService.getLogLists(userIdx, Month.of(month)));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @GetMapping("/api/users/{userIdx}/logs/{logIdx}")
    public LogResponseDto getLog(@PathVariable String userIdx, @PathVariable Long logIdx){
        return logService.getLog(userIdx,logIdx);
    }
}

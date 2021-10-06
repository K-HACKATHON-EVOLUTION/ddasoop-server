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

    @GetMapping("/api/users/{userIdx}/logs/monthly/{month}")
    public LogMonthResponseDto getMonthlyLog(@PathVariable String userIdx, @PathVariable int month){
        return logService.getMonthlyLog(userIdx, Month.of(month));
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

    @GetMapping("/api/users/{userIdx}/logs")
    public List<LogListResponseDto> getLogs(@PathVariable String userIdx){
        return logService.getLogs(userIdx);
    }

    @GetMapping("/api/users/{userIdx}/logs/{logIdx}")
    public LogResponseDto getLog(@PathVariable String userIdx, @PathVariable Long logIdx){
        return logService.getLog(userIdx,logIdx);
    }
}

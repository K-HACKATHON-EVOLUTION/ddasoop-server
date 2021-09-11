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

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LogApiController {
    private final LogService logService;

    @GetMapping("/api/users/{userIdx}/logs/monthly")
    public LogMonthResponseDto getMonthlyLog(@PathVariable Long userIdx){
        return logService.getMonthlyLog(userIdx);
    }

    @PostMapping("/api/users/{userIdx}/logs")
    public ResponseEntity<Long> saveLog(@PathVariable String userIdx, @RequestBody LogRequestDto requestDto){
        try{
            return new ResponseEntity<>(logService.saveLog(userIdx, requestDto), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/users/{userIdx}/logs")
    public List<LogListResponseDto> getLogs(@PathVariable Long userIdx){
        return logService.getLogs(userIdx);
    }

    @GetMapping("/api/users/{userIdx}/logs/{logIdx}")
    public LogResponseDto getLog(@PathVariable Long userIdx, @PathVariable Long logIdx){
        return logService.getLog(userIdx,logIdx);
    }
}

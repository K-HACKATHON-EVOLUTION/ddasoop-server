package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.LogService;
import com.evolution.ddasoop.web.dto.LogMonthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LogApiController {
    private final LogService logService;

    @GetMapping("/api/users/{userIdx}/logs/monthly")
    public LogMonthResponseDto getMonthlyLog(@PathVariable Long userIdx){
        return logService.getMonthlyLog(userIdx);
    }
}

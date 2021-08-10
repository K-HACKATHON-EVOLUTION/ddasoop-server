package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Log;
import com.evolution.ddasoop.domain.LogRepository;
import com.evolution.ddasoop.web.dto.LogMonthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;

    @Transactional(readOnly = true)
    public LogMonthResponseDto getMonthlyLog(Long userIdx){
        List<Log> logs = logRepository.findAllByUserUserIdx(userIdx);
        Double treeAmount = 0.0;
        List<LocalDate> logDates = new ArrayList<>();

        for(Log log : logs){
            treeAmount += log.getCarbon();
            if(log.getStartTime().getMonth() == LocalDateTime.now().getMonth()){
                logDates.add(log.getStartTime().toLocalDate());
            }
        }
        return LogMonthResponseDto.builder()
                .userIdx(userIdx)
                .logCnt(logs.size())
                .treeAmount(treeAmount)
                .logDates(logDates)
                .build();
    }
}

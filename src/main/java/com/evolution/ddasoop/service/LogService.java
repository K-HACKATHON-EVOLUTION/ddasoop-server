package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Log;
import com.evolution.ddasoop.domain.LogRepository;
import com.evolution.ddasoop.web.dto.LogListResponseDto;
import com.evolution.ddasoop.web.dto.LogMonthResponseDto;
import com.evolution.ddasoop.web.dto.LogResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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
        List<Log> logs = logRepository.findAllByUserUserIdxOrderByEndTimeDesc(userIdx);
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

    @Transactional(readOnly = true)
    public List<LogListResponseDto> getLogs(Long userIdx){
        List<LogListResponseDto> logs = new ArrayList<>();
        for(Log log : logRepository.findAllByUserUserIdxOrderByEndTimeDesc(userIdx)){
            LocalDate logDate = log.getStartTime().toLocalDate();
            Integer dayOfWeek = logDate.getDayOfWeek().getValue();
            Duration d = Duration.between(log.getStartTime(),log.getEndTime());
            Long hours = d.toHours();
            Long minutes = d.minusHours(hours).toMinutes();

            logs.add(LogListResponseDto.builder()
                    .logIdx(log.getLogIdx())
                    .logDate(logDate)
                    .dayOfWeek(dayOfWeek)
                    .hours(hours)
                    .minutes(minutes)
                    .carbon(log.getCarbon())
                    .build());
        }
        return logs;
    }

    @Transactional(readOnly = true)
    public LogResponseDto getLog(Long userIdx, Long logIdx){
        return new LogResponseDto(logRepository.findLogByUserUserIdxAndLogIdx(userIdx, logIdx));
    }
}

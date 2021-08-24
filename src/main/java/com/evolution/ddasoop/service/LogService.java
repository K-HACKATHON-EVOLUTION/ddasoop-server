package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.LogListResponseDto;
import com.evolution.ddasoop.web.dto.LogMonthResponseDto;
import com.evolution.ddasoop.web.dto.LogRequestDto;
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
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final ImageRepository imageRepository;
    private final BadgeRepository badgeRepository;
    private static final double TreeAmountStandard = 10.0;

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

    @Transactional
    public Long saveLog(Long userIdx, LogRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null)
            throw new IllegalArgumentException();

        Log log = Log.builder()
                .user(user)
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .startLocation(requestDto.getStartLocation())
                .endLocation(requestDto.getEndLocation())
                .distance(requestDto.getDistance())
                .build();

        logRepository.save(log);

        Double carbon = log.calculateCarbon(requestDto.getDistance());
        user.updateTotalCarbon(carbon);
        userRepository.save(user);

        Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx,TreeAmountStandard);
        Double remain = tree.updateTree(carbon);

        if(remain > 0.0){
            //현재 트리가 완성되어 뱃지화 된 경우
            Badge badge = Badge.builder()
                    .user(user)
                    .tree(tree)
                    .badgeImg(imageRepository.findImagesByImageIdx(Long.valueOf(6)))
                    .build();
            badgeRepository.save(badge);

            Tree newTree = Tree.builder()
                    .treeCarbon(remain)
                    .user(user)
                    .build();
            newTree.updateGrowth(remain);
            newTree.updateTreeImg(imageRepository.findImagesByImageIdx(newTree.getGrowth().longValue()));
            treeRepository.save(newTree);
        }

        tree.updateTreeImg(imageRepository.findImagesByImageIdx(tree.getGrowth().longValue()));
        treeRepository.save(tree);

        return log.getLogIdx();
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

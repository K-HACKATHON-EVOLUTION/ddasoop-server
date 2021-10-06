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
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final ImageRepository imageRepository;
    private final BadgeRepository badgeRepository;
    private final BadgeImageRepository badgeImageRepository;
    private static final double TreeAmountStandard = 10.0;

    @Transactional(readOnly = true)
    public LogMonthResponseDto getMonthlyLog(String userIdx, Month month){
        List<Log> logs = logRepository.findAllByUserUserIdxOrderByEndTimeDesc(userIdx);
        Double treeAmount = 0.0;
        Integer logCnt = 0;
        Set<LocalDate> logDates = new HashSet<>();

        for(Log log : logs){
            if(log.getStartTime().getMonth() == month){
                logDates.add(log.getStartTime().toLocalDate());
                treeAmount += log.getCarbon();
                logCnt++;
            }
        }
        treeAmount = Double.valueOf(Math.round(treeAmount*1000)/1000.0);
        return LogMonthResponseDto.builder()
                .userIdx(userIdx)
                .logCnt(logCnt)
                .treeAmount(treeAmount)
                .logDates(logDates)
                .build();
    }

    @Transactional
    public String saveLog(String userIdx, LogRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null)
            throw new IllegalArgumentException();

        Log log = Log.builder()
                .user(user)
                .startTime(requestDto.getStartTime())
                .endTime(requestDto.getEndTime())
                .startLocation(requestDto.getStartLocation())
                .endLocation(requestDto.getEndLocation())
                .route(requestDto.getRoute())
                .distance(requestDto.getDistance())
                .build();

        logRepository.save(log);

        Double carbon = log.calculateCarbon(requestDto.getDistance());
        user.updateTotalCarbon(carbon);

        Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx,TreeAmountStandard);
        Double remain = tree.updateTree(carbon);
        boolean win = false;

        if(remain > 0.0){
            //현재 트리 완성
            Tree newTree = Tree.builder()
                    .treeCarbon(remain)
                    .user(user)
                    .build();
            newTree.updateGrowth(remain);
            newTree.updateTreeImg(imageRepository.findImagesByImageIdx(newTree.getGrowth().longValue()));
            treeRepository.save(newTree);

            long count = badgeImageRepository.count();
            long index = (long) (Math.random() * (count * 2) + 1);

            if(index <= count){ //뱃지 획득
                Badge badge = Badge.builder()
                        .user(user)
                        .tree(tree)
                        .badgeImg(badgeImageRepository.findBadgeImageByBadgeImgIdx(index))
                        .build();
                badgeRepository.save(badge);
                win = true;
            }
        }

        tree.updateTreeImg(imageRepository.findImagesByImageIdx(tree.getGrowth().longValue()));
        treeRepository.save(tree);

        if(win){
            return "success";
        }else{
            return "lose";
        }
    }

    @Transactional(readOnly = true)
    public List<LogListResponseDto> getLogs(String userIdx){
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
    public LogResponseDto getLog(String userIdx, Long logIdx){
        return new LogResponseDto(logRepository.findLogByUserUserIdxAndLogIdx(userIdx, logIdx));
    }
}

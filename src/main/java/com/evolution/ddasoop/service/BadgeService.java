package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Badge;
import com.evolution.ddasoop.domain.BadgeRepository;
import com.evolution.ddasoop.web.dto.BadgeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Transactional(readOnly = true)
    public List<BadgeListResponseDto> getBadges(Long userIdx){
        List<BadgeListResponseDto> badges = new ArrayList<>();
        for(Badge badge: badgeRepository.findAllByUserUserIdx(userIdx)){
            badges.add(new BadgeListResponseDto(badge));
        }
        return badges;
    }
}

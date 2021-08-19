package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Badge;
import com.evolution.ddasoop.domain.BadgeRepository;
import com.evolution.ddasoop.web.dto.BadgeListResponseDto;
import com.evolution.ddasoop.web.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Transactional(readOnly = true)
    public BadgeResponseDto getBadge(Long badgeIdx) throws NoSuchElementException {
        return new BadgeResponseDto(badgeRepository.findById(badgeIdx).orElseThrow());
    }
}

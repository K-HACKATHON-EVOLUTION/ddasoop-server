package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Badge;
import com.evolution.ddasoop.domain.BadgeRepository;
import com.evolution.ddasoop.domain.User;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.BadgeListResponseDto;
import com.evolution.ddasoop.web.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<BadgeListResponseDto> getBadges(String userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);

        if(user == null) throw new IllegalArgumentException();

        List<BadgeListResponseDto> badges = new ArrayList<>();
        for(Badge badge: badgeRepository.findAllByUserUserIdx(user.getUserIdx())){
            badges.add(new BadgeListResponseDto(badge));
        }
        return badges;
    }

    @Transactional(readOnly = true)
    public BadgeResponseDto getBadge(Long badgeIdx) throws NoSuchElementException {
        return new BadgeResponseDto(badgeRepository.findById(badgeIdx).orElseThrow());
    }
}

package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.BadgeImageListResponseDto;
import com.evolution.ddasoop.web.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
    private final BadgeImageRepository badgeImageRepository;

    @Transactional(readOnly = true)
    public List<BadgeImageListResponseDto> getBadges(String userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);

        if(user == null) throw new IllegalArgumentException();

        List<BadgeImageListResponseDto> allBadges = badgeImageRepository.findAll()
                .stream()
                .map(BadgeImageListResponseDto::new)
                .collect(Collectors.toList());

        List<BadgeImageListResponseDto> userBadges = badgeRepository.findAllByUserUserIdx(user.getUserIdx())
                .stream()
                .map(BadgeImageListResponseDto::new)
                .collect(Collectors.toList());

        for(BadgeImageListResponseDto badge: userBadges){
            Optional<BadgeImageListResponseDto> findBadge = allBadges
                    .stream()
                    .filter(b -> b.getBadgeImgIdx().equals(badge.getBadgeImgIdx()))
                    .findAny();
            findBadge.ifPresent(theBadge -> findBadge.get().updateCount());
        }
        return allBadges;
    }

    @Transactional(readOnly = true)
    public BadgeResponseDto getBadge(Long badgeIdx) throws NoSuchElementException {
        return new BadgeResponseDto(badgeRepository.findById(badgeIdx).orElseThrow());
    }
}

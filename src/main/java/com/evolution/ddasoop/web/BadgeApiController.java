package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.BadgeService;
import com.evolution.ddasoop.web.dto.BadgeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BadgeApiController {
    private final BadgeService badgeService;

    @GetMapping("/api/users/{userIdx}/badges")
    public List<BadgeListResponseDto> getBadges(@PathVariable Long userIdx){
        return badgeService.getBadges(userIdx);
    }
}

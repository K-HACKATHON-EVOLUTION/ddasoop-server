package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.BadgeService;
import com.evolution.ddasoop.web.dto.BadgeListResponseDto;
import com.evolution.ddasoop.web.dto.BadgeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
public class BadgeApiController {
    private final BadgeService badgeService;

    @GetMapping("/api/users/{userIdx}/badges")
    public List<BadgeListResponseDto> getBadges(@PathVariable Long userIdx){
        return badgeService.getBadges(userIdx);
    }

    @GetMapping("/api/badges/{badgeIdx}")
    public ResponseEntity<BadgeResponseDto> getBadge(@PathVariable Long badgeIdx){
        try{
            return new ResponseEntity<>(badgeService.getBadge(badgeIdx), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

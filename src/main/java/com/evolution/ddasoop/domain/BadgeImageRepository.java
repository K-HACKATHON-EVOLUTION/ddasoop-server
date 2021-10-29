package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeImageRepository extends JpaRepository<BadgeImage, Long> {
    BadgeImage findBadgeImageByBadgeImgIdx(Long badgeImgIdx);
}

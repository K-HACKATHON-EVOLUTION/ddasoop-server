package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForestImageRepository extends JpaRepository<ForestImage, Long> {
    ForestImage findForestImageByForest(Forest forest);
}

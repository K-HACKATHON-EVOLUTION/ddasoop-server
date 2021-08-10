package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    Tree findByUserUserIdxAndTreeCarbonLessThan(Long userIdx, Double treeCarbon);
}

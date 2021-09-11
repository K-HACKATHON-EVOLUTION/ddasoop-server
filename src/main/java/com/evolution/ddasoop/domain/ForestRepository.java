package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForestRepository extends JpaRepository<Forest, Long> {
    List<Forest> findByForestNameContaining(String forest_name);

}

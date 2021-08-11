package com.evolution.ddasoop.domain;

import com.evolution.ddasoop.web.dto.MyForestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForestRepository extends JpaRepository<Forest, Long> {


}

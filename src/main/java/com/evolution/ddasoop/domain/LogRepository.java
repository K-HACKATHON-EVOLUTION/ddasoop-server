package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllByUserUserIdxOrderByEndTimeDesc(Long userIdx);
    Log findLogByUserUserIdxAndLogIdx(Long userIdx, Long logIdx);
}

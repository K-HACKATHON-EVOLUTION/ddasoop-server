package com.evolution.ddasoop.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserIdxAndDeleteFlagFalse(String userIdx);
    List<User> findAllByForest(Forest forest);
}

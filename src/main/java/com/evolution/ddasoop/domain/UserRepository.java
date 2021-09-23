package com.evolution.ddasoop.domain;

import com.evolution.ddasoop.web.dto.MemberListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserIdxAndDeleteFlagFalse(String userIdx);
    List<User> findAllByForest(Forest forest);
    //List<User> findAllByForestOrderByTotalCarbon(Forest forest);
    List<User> findAllByForestOrderByTotalCarbon(Forest forest);
}

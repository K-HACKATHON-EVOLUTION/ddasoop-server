package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.MyForestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public MyForestDto getMyForest(Long user_idx){
        Forest forest = userRepository.findById(user_idx).get().getForest();
        MyForestDto myForestDto =MyForestDto.builder()
                .forestIdx(forest.getForestIdx())
                .forestImg(forest.getForestImg())
                .forestName(forest.getForestName())
                .build();
        return myForestDto;
    }
}

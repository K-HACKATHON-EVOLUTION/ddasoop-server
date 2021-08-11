package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.MyForestDto;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserMainResponseDto getMainInfo(Long userIdx){
        return new UserMainResponseDto(userRepository.findUserByUserIdx(userIdx));
    }

    @Transactional
    public MyForestDto getMyForest(Long user_idx) {
        Forest forest = userRepository.findById(user_idx).get().getForest();
        MyForestDto myForestDto = MyForestDto.builder()
                .forestName(forest.getForestName())
                .forestIdx(forest.getForestIdx())
                .forestImg(forest.getForestImg())
                .build();
        return myForestDto;
    }
}

package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final double TreeAmountStandard = 10.0;


    @Transactional(readOnly = true)
    public UserMainResponseDto getMainInfo(Long userIdx){
        return new UserMainResponseDto(userRepository.findUserByUserIdx(userIdx));
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userIdx){
        User user = userRepository.findUserByUserIdx(userIdx);
        Image treeImg = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx, TreeAmountStandard).getTreeImg();

        return UserResponseDto.builder()
                .userIdx(user.getUserIdx())
                .userName(user.getUserName())
                .treeImg(treeImg.getFilePath()+treeImg.getOriginalFileName())
                .build();
    }
}

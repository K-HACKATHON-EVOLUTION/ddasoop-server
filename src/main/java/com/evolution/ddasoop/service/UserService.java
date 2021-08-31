package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import com.evolution.ddasoop.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final ForestRepository forestRepository;
    private final double TreeAmountStandard = 10.0;


    @Transactional(readOnly = true)
    public UserMainResponseDto getMainInfo(Long userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }

        return new UserMainResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }

        Image treeImg = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx, TreeAmountStandard).getTreeImg();

        return UserResponseDto.builder()
                .userIdx(user.getUserIdx())
                .userName(user.getUserName())
                .treeImg(treeImg.getFilePath()+treeImg.getOriginalFileName())
                .build();
    }

    @Transactional
    public String deleteUser(Long userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.updateDeleteFlag();
        return "success";
    }

    @Transactional
    public String updateUserName(Long userIdx, UserUpdateRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        String userName = requestDto.getUserName();
        if(user == null || userName == null || userName.length() < 1){
            throw new IllegalArgumentException();
        }

        user.updateUserName(userName);
        return "success";
    }

    @Transactional
    public String addForest(Long userIdx, Long forestIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forestIdx);
        if(user == null || forest == null){
            throw new IllegalArgumentException();
        }

        user.updateForest(forest);
        return "success";
    }

    @Transactional
    public String deleteForest(Long userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.deleteForest();
        return "success";
    }
}

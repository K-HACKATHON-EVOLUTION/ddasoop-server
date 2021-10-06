package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.TreeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TreeService {
    private final TreeRepository treeRepository;
    private final UserRepository userRepository;
    private static final double TreeAmountStandard = 10.0;

    @Transactional
    public TreeResponseDto getCurrentTree(String userIdx){
        Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx,TreeAmountStandard);
        return new TreeResponseDto(tree);
    }

    @Transactional
    public String updateTreeName(String userIdx, String newName) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);

        if(user == null){
            throw new IllegalArgumentException();
        }

        Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(user.getUserIdx(),TreeAmountStandard);

        tree.updateTreeName(newName);

        return "success";
    }
}

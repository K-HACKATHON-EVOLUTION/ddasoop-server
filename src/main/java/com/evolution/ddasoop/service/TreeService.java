package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Image;
import com.evolution.ddasoop.domain.ImageRepository;
import com.evolution.ddasoop.domain.Tree;
import com.evolution.ddasoop.domain.TreeRepository;
import com.evolution.ddasoop.web.dto.TreeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TreeService {
    private final TreeRepository treeRepository;
    private static final double TreeAmountStandard = 10.0;

    @Transactional
    public TreeResponseDto getCurrentTree(Long userIdx){
        Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(userIdx,TreeAmountStandard);
        return new TreeResponseDto(tree);
    }
}

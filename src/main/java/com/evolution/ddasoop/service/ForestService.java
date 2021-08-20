package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestRepository;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.ForestListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ForestService {

    private final ForestRepository forestRepository;
    private final UserRepository userRepository;

    public List<Forest> findAll(){
        return forestRepository.findAll();
    }

    @Transactional
    public String updateName(long forest_idx, String forest_name){
        Forest forest = forestRepository.findById(forest_idx).get();
        forest.updateName(forest_name);
        return "숲 이름이 변경되었습니다.";
    }

    @Transactional
    public String updateIntro(long forest_idx, String forest_intro){
        Forest forest = forestRepository.findById(forest_idx).get();
        forest.updateIntro(forest_intro);
        return "숲 한 줄 소개가 변경되었습니다.";
    }

}

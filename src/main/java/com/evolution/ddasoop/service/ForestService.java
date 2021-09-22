package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestRepository;
import com.evolution.ddasoop.domain.User;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.ForestListResponseDto;
import com.evolution.ddasoop.web.dto.SearchForestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ForestService {

    private final ForestRepository forestRepository;
    private final UserRepository userRepository;

    public List<Forest> findAll(){
        return forestRepository.findAll();
    }

    @Transactional
    public List<ForestListResponseDto> getAllForest(){
     List<ForestListResponseDto> forestListResponseDtoList = new ArrayList<>();

     return forestListResponseDtoList;
    }

    @Transactional
    public List<SearchForestDto> searchForests(String forestName){
        return forestRepository.findByForestNameContaining(forestName).stream()
                .map(SearchForestDto::new)
                .collect(Collectors.toList());
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

    @Transactional
    public String deleteForestMember(long forest_idx, String user_idx){
        User user = userRepository.findById(user_idx).get();
        if(user.getForest().getForestIdx() == forest_idx){
            user.setForest(null);
            return "술의 그룹원을 내보냈습니다.";
        } else return "해당 그룹원은 숲의 멤버가 아닙니다.";
    }

    @Transactional
    public String deleteForest(long forest_idx){
        Forest forest = forestRepository.findById(forest_idx).get();
        List<User> userList = userRepository.findAllByForest(forest);

        int userListLenghth = userList.size();
        for(int i = 0 ; i<userListLenghth; i++){
            userList.get(i).setForest(null);
        }
        forest.updateDeleteFlag(true);

        return "숲이 삭제되었습니다.";
    }

}

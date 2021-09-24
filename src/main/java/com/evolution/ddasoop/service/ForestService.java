package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestRepository;
import com.evolution.ddasoop.domain.User;
import com.evolution.ddasoop.domain.UserRepository;
import com.evolution.ddasoop.web.dto.*;
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

    @Transactional
    public List<ForestListResponseDto> getAllForest(){
     List<ForestListResponseDto> forests = new ArrayList<>();
     for (Forest forest: forestRepository.findAllByDeleteFlagFalseOrderByForestName()){
            forests.add(ForestListResponseDto.builder()
                            .forestIdx(forest.getForestIdx())
                            .leader(forest.getLeader())
                            .forestName(forest.getForestName())
                            .size(forest.getSize())
                            .forestImg(forest.getForestImg())
                            .deleteFlag(forest.getDeleteFlag())
                    .build());
     }
     return forests;
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

    @Transactional
    public String saveForest(String user_idx, ForestSaveDto forestSaveDto){
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx);
        if(user.getForest() == null){
            Forest forest = Forest.builder()
                    .forestName(forestSaveDto.getForestName())
                    .leader(user_idx)
                    .forestIntro(forestSaveDto.getForestIntro())
                    .forestImg(forestSaveDto.getForestImg())
                    .size(forestSaveDto.getSize())
                    .deleteFlag(Boolean.FALSE)
                    .build();

            forestRepository.save(forest);
            user.setForest(forest);
            return "숲이 생성되었습니다";
        }
        else return"이미 가입된 숲이 존재합니다!";
    }

    @Transactional
    public ForestMemberListDto getForest(Long forest_idx){
        Forest forest = forestRepository.findById(forest_idx).get();
        Double trees = 0.0;

        List<MemberListDto> memberList = new ArrayList<>();
        for(User user: userRepository.findAllByForestOrderByTotalCarbon(forest)){
            memberList.add(MemberListDto.builder()
                            .user_name(user.getUserName())
                            .user_carbon(user.getTotalCarbon())
                    .build());
        }

        int size = memberList.size();
        for(int i =0; i<size;i++){
            trees = trees + memberList.get(i).getUser_carbon();
        }

        ForestMemberListDto forestMemberListDto = ForestMemberListDto.builder()
                .member(memberList)
                .leader(forest.getLeader())
                .total_trees(trees)
                .build();

        return forestMemberListDto;
    }
}

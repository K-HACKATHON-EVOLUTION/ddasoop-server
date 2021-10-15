package com.evolution.ddasoop.service;


import com.evolution.ddasoop.web.dto.MyForestDto;
import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import com.evolution.ddasoop.web.dto.UserSaveRequestDto;
import com.evolution.ddasoop.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TreeRepository treeRepository;
    private final ForestRepository forestRepository;
    private final ForestImageRepository forestImageRepository;
    private final ImageRepository imageRepository;
    private final BadgeImageRepository badgeImageRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public Long saveUser(UserSaveRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(requestDto.getUserIdx());
        if(user != null){
            throw new IllegalArgumentException();
        }
        user = User.builder()
                .userIdx(requestDto.getUserIdx())
                .userName(requestDto.getUserName())
                .deleteFlag(false)
                .forest(null)
                .totalCarbon(0.0)
                .build();
        userRepository.save(user);

        long count = badgeImageRepository.count();
        long index = (long) (Math.random() * count + 1);

        Badge badge = Badge.builder()
                .user(user)
                .tree(null)
                .badgeImg(badgeImageRepository.findBadgeImageByBadgeImgIdx(index))
                .build();
        badgeRepository.save(badge);

        treeRepository.save(Tree.builder()
                .user(user)
                .treeName("나무")
                .treeCarbon(0.0)
                .growth(1)
                .treeImg(imageRepository.findImagesByImageIdx(Long.valueOf(1)))
                .build());

        return badge.getBadgeIdx();
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(String userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);

        if(user == null){
            throw new IllegalArgumentException();
        }

        List<UserResponseDto.TreeListResponseDto> trees = new ArrayList<>();

        for(Tree tree : treeRepository.findAllByUserUserIdx(user.getUserIdx())){
            trees.add(UserResponseDto.TreeListResponseDto.builder()
                    .treeIdx(tree.getTreeIdx())
                    .treeImg(tree.getTreeImg().getFilePath())
                    .treeName(tree.getTreeName())
                    .treeCarbon(tree.getTreeCarbon())
                    .build());
        }

        return UserResponseDto.builder()
                .userIdx(user.getUserIdx())
                .userName(user.getUserName())
                .trees(trees)
                .build();
    }

    @Transactional
    public String deleteUser(String userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.updateDeleteFlag();
        return "success";
    }

    @Transactional
    public String updateUserName(String userIdx, UserUpdateRequestDto requestDto) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        String userName = requestDto.getUserName();
        if(user == null || userName == null || userName.length() < 1){
            throw new IllegalArgumentException();
        }

        user.updateUserName(userName);
        return "success";
    }

    @Transactional
    public String addForest(String userIdx, Long forestIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forestIdx);
        if(user == null || forest == null){
            throw new IllegalArgumentException();
        }

        user.updateForest(forest);
        return "success";
    }

    @Transactional
    public String deleteForest(String userIdx) throws IllegalArgumentException{
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(userIdx);
        if(user == null){
            throw new IllegalArgumentException();
        }
        user.deleteForest();
        return "success";
    }

    @Transactional
    public MyForestDto getMyForest(String user_idx) {
        Forest forest = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx).getForest();
        ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);
        MyForestDto myForestDto = MyForestDto.builder()
                .forestName(forest.getForestName())
                .forestIdx(forest.getForestIdx())
                .forestImg(forestImage.getFilePath())
                .build();
        return myForestDto;
    }

}

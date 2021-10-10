package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ForestService {

    private final ForestRepository forestRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final ForestImageRepository forestImageRepository;

    @Transactional
    public List<ForestListResponseDto> getAllForest(){
     List<ForestListResponseDto> forests = new ArrayList<>();
     List<User> users = new ArrayList<>();

     for (Forest forest: forestRepository.findAllByDeleteFlagFalseOrderByForestName()){
         double forest_carbon=0;
         for(User user: userRepository.findAllByDeleteFlagIsFalseAndForest(forest)){
            forest_carbon += user.getTotalCarbon();
         }

         ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);

         forests.add(ForestListResponseDto.builder()
                 .forestIdx(forest.getForestIdx())
                 .leader(forest.getLeader())
                 .forestName(forest.getForestName())
                 .forestImg(forestImage.getFilePath())
                 .size(forest.getSize())
                 .deleteFlag(forest.getDeleteFlag())
                 .carbon(forest_carbon)
                 .build());
     }

        forests.sort(Comparator.comparing(ForestListResponseDto::getCarbon).reversed());

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
    public String createForest(String user_idx, ForestSaveDto forestSaveDto, MultipartFile photo) throws IOException {
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx);
        if(user.getForest() == null){
            Forest forest = Forest.builder()
                    .forestName(forestSaveDto.getForestName())
                    .leader(user_idx)
                    .forestIntro(forestSaveDto.getForestIntro())
                    .size(10)
                    .deleteFlag(Boolean.FALSE)
                    .build();
            forestRepository.save(forest);
            user.setForest(forest);
            ForestImage forestImage = s3Service.upload(photo,forest);
            forestImageRepository.save(forestImage);
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
                            .user_idx(user.getUserIdx())
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

    @Transactional
    public String updateForestPhoto(Long forest_idx, MultipartFile uploadFile){
        String newPhoto;
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forest_idx);
        if(forest == null){
            return "숲이 존재하지 않습니다. forest_idx: " +forest_idx;
        }
        try{
            String oldPhoto = forest.getForestImg();
            newPhoto = s3Service.update(oldPhoto,uploadFile);

            if(newPhoto!=null){
                forest.updatePhotoUrl("https://"+s3Service.CLOUD_FRONT_DOMAIN_NAME+"/"+newPhoto);
                s3Service.delete(oldPhoto);
            } else {
                return "file type is not proper or is corrupted";
            }

        } catch (Exception e){
            System.out.println("file exception");
            return "error occured during upload" + e.getMessage();
        }
        return "숲 사진이 변경되었습니다";
    }

    @Transactional
    public String updateForestImg(Long forest_idx, MultipartFile uploadFile){
        String newPhoto;
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forest_idx);
        ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);
        if(forest == null){
            return "숲이 존재하지 않습니다. forest_idx: " +forest_idx;
        }
        try{
            String oldPhoto = forest.getForestImg();
            newPhoto = s3Service.update(oldPhoto,uploadFile);

            if(newPhoto!=null){
                forestImage.setOriginalFileName(newPhoto);
                forestImage.updatePath("http://"+s3Service.CLOUD_FRONT_DOMAIN_NAME+"/"+newPhoto);
                s3Service.delete(oldPhoto);
            } else {
                return "file type is not proper or is corrupted";
            }

        } catch (Exception e){
            System.out.println("file exception");
            return "error occured during upload " + e.getMessage();
        }
        return "숲 사진이 변경되었습니다";
    }
}

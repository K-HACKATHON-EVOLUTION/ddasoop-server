package com.evolution.ddasoop.service;

import com.evolution.ddasoop.domain.*;
import com.evolution.ddasoop.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ForestService {

    private final ForestRepository forestRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final ForestImageRepository forestImageRepository;
    private final TreeRepository treeRepository;

    @Transactional
    public List<ForestListResponseDto> getAllForest(){
     List<ForestListResponseDto> forests = new ArrayList<>();
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
    public ForestListResponseDto getMyForest(String user_idx){
        Forest forest = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx).getForest();
        ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);
        double forest_carbon=0;
        for(User user: userRepository.findAllByDeleteFlagIsFalseAndForest(forest)){
            forest_carbon += user.getTotalCarbon();
        }

        ForestListResponseDto forestListResponseDto = ForestListResponseDto.builder()
                .forestIdx(forest.getForestIdx())
                .leader(forest.getLeader())
                .forestName(forest.getForestName())
                .forestImg(forestImage.getFilePath())
                .size(forest.getSize())
                .deleteFlag(forest.getDeleteFlag())
                .carbon(forest_carbon)
                .build();

        return forestListResponseDto;
    }

    @Transactional
    public List<ForestListResponseDto> search(String forest_name){
        List<ForestListResponseDto> forestListResponseDtos = new ArrayList<>();
        for(Forest forest: forestRepository.findByForestNameContaining(forest_name)){

            ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);

            double total_carbon=0;
            for(User user: userRepository.findAllByDeleteFlagIsFalseAndForest(forest)){
                total_carbon += user.getTotalCarbon();
            }
            forestListResponseDtos.add(ForestListResponseDto.builder()
                    .forestIdx(forest.getForestIdx())
                    .leader(forest.getLeader())
                    .forestName(forest.getForestName())
                    .forestImg(forestImage.getFilePath())
                    .size(forest.getSize())
                    .deleteFlag(forest.getDeleteFlag())
                    .carbon(total_carbon)
                    .build());
        }
        return forestListResponseDtos;

    }

    @Transactional
    public String updateName(long forest_idx, String forest_name){
        Forest forest = forestRepository.findById(forest_idx).get();
        forest.updateName(forest_name);
        return "??? ????????? ?????????????????????.";
    }

    @Transactional
    public String updateIntro(long forest_idx, String forest_intro){
        Forest forest = forestRepository.findById(forest_idx).get();
        forest.updateIntro(forest_intro);
        return "??? ??? ??? ????????? ?????????????????????.";
    }

    @Transactional
    public String deleteForestMember(long forest_idx, String user_idx){
        User user = userRepository.findById(user_idx).get();
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forest_idx);
        if(user.getForest().getForestIdx() == forest_idx){
            user.setForest(null);
            forest.setSize(forest.getSize()-1);
            return "?????? ???????????? ??????????????????.";
        } else return "?????? ???????????? ?????? ????????? ????????????.";
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

        return "?????? ?????????????????????.";
    }


    /*@Transactional
    public String create(String user_idx, ForestSaveDto forestSaveDto,MultipartFile photo) throws Exception{
            User user = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx);
            if(user.getForest()!=null){
            throw new Exception("?????? ???????????? ?????? ???????????????!");
            }

            Forest forest = new Forest(
                    forestSaveDto.getForestName(),
                    user_idx,
                    1,
                    null,
                    false,
                    forestSaveDto.getForestIntro()
            );

            forestRepository.save(forest);

            user.setForest(forest);

            ForestImage forestImage = s3Service.upload(photo,forest);
            if(!forestImage.getFilePath().isEmpty()){
                forestImageRepository.save(forestImage);
            }
            return "?????? ?????????????????????";
    }*/

    @Transactional
    public String create(String user_idx, ForestSaveDto forestSaveDto) {
        User user = userRepository.findByUserIdxAndDeleteFlagFalse(user_idx);

            Forest forest = Forest.builder()
                    .forestIntro(forestSaveDto.getForestIntro())
                    .leader(user_idx)
                    .forestName(forestSaveDto.getForestName())
                    .size(1)
                    .deleteFlag(false)
                    .forestImg(null)
                    .build();

            forestRepository.save(forest);

            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");

            ForestImage forestImage = ForestImage.builder()
                    .forest(forest)
                    .filePath(forestSaveDto.getForestImg())
                    .fileSize(Long.valueOf(1000))
                    .originalFileName("forest_"+forest.getForestIdx()+"_"+date)
                    .build();

            forestImageRepository.save(forestImage);

            user.updateForest(forest);
            return "?????? ?????????????????????";
        }


    @Transactional
    public ForestMemberListDto getForest(Long forest_idx, String user_idx){
        Forest forest = forestRepository.findById(forest_idx).get();
        Double trees = 0.0;

        List<MemberListDto> memberList = new ArrayList<>();
        for(User user: userRepository.findAllByForestOrderByTotalCarbon(forest)){
            Tree tree = treeRepository.findByUserUserIdxAndTreeCarbonLessThan(user.getUserIdx(),Tree.MAX_TREE);
            memberList.add(MemberListDto.builder()
                            .user_name(user.getUserName())
                            .user_carbon(user.getTotalCarbon())
                            .user_idx(user.getUserIdx())
                            .user_treeImg(tree.getTreeImg().getFilePath())
                    .build());
        }

        int size = memberList.size();
        for(int i =0; i<size;i++){
            trees = trees + memberList.get(i).getUser_carbon();
        }

        int inList =0, own =0;
        String Leader = forest.getLeader();

        for(MemberListDto memberListDto:memberList){
            if(memberListDto.getUser_idx().equals(user_idx)){
                inList =1;
                break;
            }
        }

        if(Leader.equals(user_idx)==true){
            own = 0;
        }else if(Leader.equals(user_idx)==false&&inList==1) {
            own =1;
        }else if(Leader.equals(user_idx)==false&&inList==0)
            own =2;
        else own = 0;

        ForestMemberListDto forestMemberListDto = ForestMemberListDto.builder()
                .member(memberList)
                .leader(forest.getLeader())
                .total_trees(trees)
                .own(own)
                .forest_intro(forest.getForestIntro())
                .build();

        memberList.sort(Comparator.comparing(MemberListDto::getUser_carbon));
        return forestMemberListDto;
    }


    /*@Transactional
    public String updateForestImg(Long forest_idx, MultipartFile uploadFile){
        String newPhoto;
        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forest_idx);
        ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);
        if(forest == null){
            return "?????? ???????????? ????????????. forest_idx: " +forest_idx;
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
        return "??? ????????? ?????????????????????";
    }*/

    @Transactional
    public String updateForestImg(Long forest_idx, String uploadFile){
        String newPhoto = uploadFile;

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");

        Forest forest = forestRepository.findByForestIdxAndDeleteFlagFalse(forest_idx);
        ForestImage forestImage = forestImageRepository.findForestImageByForest(forest);
        if(forest == null){
            return "?????? ???????????? ????????????. forest_idx: " + forest_idx;
        }
        try{
            if(newPhoto!=null){
                forestImage.setOriginalFileName("forest_" + forest_idx + "_"+ date.format(new Date()));
                forestImage.updatePath(newPhoto);
            } else {
                return "file type is not proper or is corrupted";
            }

        } catch (Exception e){
            System.out.println("file exception");
            return "error occured during upload " + e.getMessage();
        }
        return "??? ????????? ?????????????????????";
    }


}

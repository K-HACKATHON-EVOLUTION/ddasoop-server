package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.ForestService;
import com.evolution.ddasoop.web.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class ForestController {

    private final ForestService forestService;

    //1. Group List 페이지 숲 목록 불러오기
    @GetMapping("/forests")
    public List<ForestListResponseDto> getForestList(){
        return forestService.getAllForest();
    }

    //2. Group List 페이지 MY 숲 가져오기 **내 그룹 반환(리스트 컨테이너 형태)**
    @GetMapping("/users/{user_idx}/forest")
    public ForestListResponseDto getMyForest(@PathVariable("user_idx") String user_idx){
        return forestService.getMyForest(user_idx);
    }

    //3.그룹 하나 가져오기
    @GetMapping("/forest/{forest_idx}/{user_idx}/user")
    public ForestMemberListDto ForestUserList(@PathVariable("forest_idx") Long forest_idx,@PathVariable("user_idx") String user_idx){
        return forestService.getForest(forest_idx, user_idx);
    }

    //4. 숲 검색하기 그룹 검색(특정 텍스트값 그룹 이름에 포함하고 있는 그룹을 리스트로 반환)
    @GetMapping("/forest/search")
    public List<ForestListResponseDto> searchForest(@RequestParam(value="forest_name", required = false, defaultValue = "") String forestName){
        return forestService.search(forestName);
    }


    //숲 생성하기 다시
    /*@PostMapping("/forests/{user_idx}")
    public String makeForest(@PathVariable String user_idx,
                             @RequestParam(value = "photo",required = false) MultipartFile photo,
                             @RequestParam(value = "forestSaveDto") String forestSaveDto) throws Exception {
        ForestSaveDto forestDto = new ObjectMapper().readValue(forestSaveDto,ForestSaveDto.class);
        return forestService.create(user_idx, forestDto, photo);
    }*/

    @PostMapping("/forests/{user_idx}")
    public String makeForest(@PathVariable String user_idx,
                             @RequestBody ForestSaveDto forestSaveDto) throws Exception {
        return forestService.create(user_idx, forestSaveDto);
    }

    // 6.  그룹 사진 수정
   /* @PatchMapping("/forests/{forest_idx}/photo")
    public String updateForestPhoto(@PathVariable Long forest_idx, @RequestParam("uploadFile") MultipartFile uploadFile){
        return forestService.updateForestImg(forest_idx,uploadFile);
    }*/

    @PatchMapping("/forests/{forest_idx}/photo")
    public String updateForestPhoto(@PathVariable Long forest_idx,@RequestBody Map<String, String> param){
        String forest_img = param.get("forestImg");
        return forestService.updateForestImg(forest_idx,forest_img);
    }


    //7.  그룹 이름 편집
    @PatchMapping("/forests/{forest_idx}/name")
    public String updateForestName(@PathVariable("forest_idx") long forest_idx, @RequestBody Map<String, String> param){
        String forest_name = param.get("forest_name");
        return forestService.updateName(forest_idx, forest_name);
    }

    //8.  그룹 소개 편집
    @PatchMapping("/forests/{forest_idx}/intro")
    public String updateForestIntro(@PathVariable("forest_idx") long forest_idx, @RequestBody Map<String, String> param){
        String forest_intro = param.get("forest_intro");
        return forestService.updateIntro(forest_idx, forest_intro);
    }

    // 9.  그룹 삭제(리더&그룹원의 그룹 탈퇴)
    @DeleteMapping("/forests/{forest_idx}")
    public String deleteForest(@PathVariable("forest_idx") long forest_idx){
        return forestService.deleteForest(forest_idx);
    }

    //10.  그룹원 삭제(그룹원의 그룹 탈퇴)
    @DeleteMapping("/forests/{forest_idx}/users/{user_idx}")
    public String deleteForestMember(@PathVariable("forest_idx") long forest_idx, @PathVariable("user_idx") String user_idx){
        return forestService.deleteForestMember(forest_idx, user_idx);
    }

}

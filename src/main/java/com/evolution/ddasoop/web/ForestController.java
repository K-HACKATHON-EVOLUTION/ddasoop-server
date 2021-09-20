package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.ForestService;
import com.evolution.ddasoop.service.S3Service;
import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.ForestListResponseDto;
import com.evolution.ddasoop.web.dto.ForestMemberListDto;
import com.evolution.ddasoop.web.dto.MyForestDto;
import com.evolution.ddasoop.web.dto.SearchForestDto;
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
    private final UserService userService;
    private S3Service s3Service;

    //1. Group List 페이지 숲 목록 불러오기 **그룹 리스트 반환(탄소저감량순)**
    // !!이 목록 아니잖아 멍청아./..다시해라.. 탄소 저감량 순 아직 안 넣음
    /*@GetMapping("/forests")
    public ResponseEntity<ForestListResponseDto> ForestList(){
        final ForestListResponseDto forestListResponseDto = ForestListResponseDto.builder()
                .forestList(forestService.findAll())
                .build();
        return ResponseEntity.ok(forestListResponseDto);
    }*/

    @GetMapping("/forests")
    public List<ForestListResponseDto> getForestList(){
        return forestService.getAllForest();
    }

    //2. Group List 페이지 MY 숲 가져오기 **내 그룹 반환(리스트 컨테이너 형태)**
    @GetMapping("/users/{user_idx}/forest")
    public MyForestDto getMyForest(@PathVariable("user_idx") String user_idx){
        return userService.getMyForest(user_idx);
    }

    //3.그룹 하나 가져오기 (리스트) 그룹 하나 반환 '그룹원 리스트(탄소저감량순 정렬), 그룹 전체 나무 그루 수, 그룹장'
    /* forest_idx를 받아와서 user 중 같은 forest_idx를 가지는 애들을 찾음
    * 해당 애들을 리스트에 넣어서 반환 */
    @GetMapping("/forest/{forest_idx}/user")
    public void ForestUserList(@PathVariable("forest_idx") Long forest_idx){

    }

    //4. 숲 검색하기 그룹 검색(특정 텍스트값 그룹 이름에 포함하고 있는 그룹을 리스트로 반환)
    @GetMapping("/forest/search")
    public List<SearchForestDto> searchForest(@RequestParam(value="forest_name", required = false, defaultValue = "") String forestName){
        return forestService.searchForests(forestName);
    }

    //5. 그룹 생성(사진, 숲 이름, 숲 소개, 리더값 받기)
    @PostMapping("/forests/{user_idx}")
    public void makeForest(){}

    // 6.  그룹 사진 수정
    @PatchMapping("/forests/{forest_idx}/photo")
    public String updateForestPhoto(MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(file);
        return "redirect:/forests/{forest_idx}/photo";
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

package com.evolution.ddasoop.web;

import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.service.ForestService;
import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.ForestListResponseDto;
import com.evolution.ddasoop.web.dto.MyForestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class ForestController {

    private final ForestService forestService;
    private final UserService userService;

    //1. Group List 페이지 숲 목록 불러오기
    /*에러 체크하기, 탄소 저감량 순 아직 안 넣음*/
    @GetMapping("/forest/list")
    public ResponseEntity<ForestListResponseDto> ForestList(){
        final ForestListResponseDto forestListResponseDto = ForestListResponseDto.builder()
                .forestList(forestService.findAll()).build();
        return ResponseEntity.ok(forestListResponseDto);
    }

    //2. Group List 페이지 MY 숲 가져오기
    /*user애서 forest_idx 찾음
    * 해당 forest_idx를 받아와서 forest에서 찾음
    * 그걸 반환함
    * */
    @GetMapping("/users/{user_idx}/forest")
    public MyForestDto getMyForest(@PathVariable("user_idx") Long user_idx){
        return userService.getMyForest(user_idx);
    }

    //3. Other Group 페이지 그룹원 가져오기 (리스트)
    /* forest_idx를 받아와서 user 중 같은 forest_idx를 가지는 애들을 찾음
    * 해당 애들을 리스트에 넣어서 반환 */
    @GetMapping("/forest/{forest_idx}/user")
    public void ForestUserList(@PathVariable("forest_idx") Long forest_idx){
    }

    //4. 숲 검색하기
    /*
    * 이름으로 검색????*/
    @GetMapping("/forest/{forest_idx}")
    public void SearchForest(@PathVariable("forest_idx") Long forest_idx){

    }



}

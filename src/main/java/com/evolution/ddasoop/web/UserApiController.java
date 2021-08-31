package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import com.evolution.ddasoop.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;

    @GetMapping("/{userIdx}/main")
    public ResponseEntity<UserMainResponseDto> getMainInfo(@PathVariable Long userIdx){
        try{
            return new ResponseEntity<>(userService.getMainInfo(userIdx),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<Object> getUser(@PathVariable Long userIdx){
        try{
            return ResponseEntity.ok().body(userService.getUser(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/userName")
    public ResponseEntity<Object> updateUserName(@PathVariable Long userIdx, @RequestBody UserUpdateRequestDto requestDto){
        try{
            return ResponseEntity.ok().body(userService.updateUserName(userIdx, requestDto));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/forest/{forestIdx}")
    public ResponseEntity<Object> addForest(@PathVariable Long userIdx, @PathVariable Long forestIdx){
        try{
            return ResponseEntity.ok().body(userService.addForest(userIdx, forestIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/forest")
    public ResponseEntity<Object> deleteForest(@PathVariable Long userIdx){
        try{
            return ResponseEntity.ok().body(userService.deleteForest(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}

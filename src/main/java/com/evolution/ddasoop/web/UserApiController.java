package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.UserMainResponseDto;
import com.evolution.ddasoop.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/users/{userIdx}/main")
    public ResponseEntity<UserMainResponseDto> getMainInfo(@PathVariable Long userIdx){
        try{
            return new ResponseEntity<>(userService.getMainInfo(userIdx),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/users/{userIdx}")
    public ResponseEntity<Object> getUser(@PathVariable Long userIdx){
        try{
            return ResponseEntity.ok().body(userService.getUser(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

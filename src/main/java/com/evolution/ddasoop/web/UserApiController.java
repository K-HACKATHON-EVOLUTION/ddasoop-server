package com.evolution.ddasoop.web;

import com.evolution.ddasoop.service.UserService;
import com.evolution.ddasoop.web.dto.UserSaveRequestDto;
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
  
    @PostMapping("")
    public ResponseEntity<Object> saveUser(@RequestBody UserSaveRequestDto requestDto){
        try{
            return new ResponseEntity<>(userService.saveUser(requestDto),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity<Object> getUser(@PathVariable String userIdx){
        try{
            return ResponseEntity.ok().body(userService.getUser(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @DeleteMapping("/{userIdx}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userIdx){
        try{
            return ResponseEntity.ok().body(userService.deleteUser(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/userName")
    public ResponseEntity<Object> updateUserName(@PathVariable String userIdx, @RequestBody UserUpdateRequestDto requestDto){
        try{
            return ResponseEntity.ok().body(userService.updateUserName(userIdx, requestDto));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/forest/{forestIdx}")
    public ResponseEntity<Object> addForest(@PathVariable String userIdx, @PathVariable Long forestIdx){
        try{
            return ResponseEntity.ok().body(userService.addForest(userIdx, forestIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }

    @PatchMapping("/{userIdx}/forest")
    public ResponseEntity<Object> deleteForest(@PathVariable String userIdx){
        try{
            return ResponseEntity.ok().body(userService.deleteForest(userIdx));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
        }
    }
}

package com.evolution.ddasoop.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {
    @GetMapping("/test")
    public String testMethod(){
        return "test Success";
    }
}

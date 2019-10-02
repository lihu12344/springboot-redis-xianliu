package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){

        return "success";
    }

    @RequestMapping("/hello2")
    public String error(){
        return "请稍后重试哟";
    }
}

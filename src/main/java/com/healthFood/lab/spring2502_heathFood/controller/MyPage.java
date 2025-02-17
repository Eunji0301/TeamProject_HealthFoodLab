package com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyPage {
    @RequestMapping("/usr/myPage/mypage")
    public String showMypage() {
        return "usr/myPage/mypage";
    }
}

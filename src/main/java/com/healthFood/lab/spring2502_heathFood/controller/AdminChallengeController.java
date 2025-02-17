package com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminChallengeController {
    @RequestMapping("/adm/challenge/challengeList")
    public String showChallengeList() {
        return "adm/challenge/challengeList";
    }

    @RequestMapping("/adm/challenge/challengeModify")
    public String showChallengeModify() {
        return "adm/challenge/challengeModify";
    }

    @RequestMapping("/adm/challenge/challengeWrite")
    public String showChallengeWrite() {
        return "adm/challenge/challengeWrite";
    }
}

package com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChallengeController {
    @RequestMapping("/usr/challenge/challengeList")
    public String showChallengeList() {
        return "usr/challenge/challengeList";
    }

    @RequestMapping("/usr/challenge/challengeModify")
    public String showChallengeModify() {
        return "usr/challenge/challengeModify";
    }

    @RequestMapping("/usr/challenge/challengeWrite")
    public String showChallengeWrite() {
        return "usr/challenge/challengeWrite";
    }

}

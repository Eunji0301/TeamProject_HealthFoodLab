package com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyChallengeController {
    @RequestMapping("/usr/myChallenge/myChallengeList")
    public String showMyChallengeList() {
        return "usr/myChallenge/myChallengeList";
    }

    @RequestMapping("/usr/myChallenge/myChallengeWrite")
    public String showMyChallengeWrite() {
        return "usr/myChallenge/myChallengeWrite";
    }

    @RequestMapping("/usr/myChallenge/myChallengeModify")
    public String showMyChallengeModify() {
        return "usr/myChallenge/myChallengeModify";
    }

    @RequestMapping("/usr/myChallenge/myChallengeDetail")
    public String showMyChallengeDetail() {
        return "usr/myChallenge/myChallengeDetail";
    }

    @RequestMapping("/usr/myChallenge/myChallengeContents")
    public String showMyChallengeContents() {
        return "usr/myChallenge/myChallengeContents";
    }

}

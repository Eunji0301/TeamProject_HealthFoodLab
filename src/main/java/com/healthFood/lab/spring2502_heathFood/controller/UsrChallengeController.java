package com.healthFood.lab.spring2502_heathFood.controller;

import com.healthFood.lab.spring2502_heathFood.service.ChallengeService;
import com.healthFood.lab.spring2502_heathFood.vo.ChallengeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UsrChallengeController {
    @Autowired
    private ChallengeService challengeService;

    // 챌린지 목록 조회
    @RequestMapping("/usr/challenge/challengeList")
    public String showchallengeList(Model model) {
        List<ChallengeVo> challengeList = challengeService.getAllChallenges();
        model.addAttribute("challengeList", challengeService.getAllChallenges());
        return "usr/challenge/challengeList"; // 챌린지 목록 뷰
    }

    @RequestMapping("/usr/challenge/challengeContents/{ciIdx}")
    public String showChallengeContents(@PathVariable int ciIdx, Model model) {
        System.out.println("ciIdx: " + ciIdx);  // 로그로 확인
        ChallengeVo challenge = challengeService.getChallengeById(ciIdx);
        model.addAttribute("challenge", challenge);  // 챌린지 정보 모델에 추가
        model.addAttribute("ciIdx", ciIdx);  // ciIdx를 모델에 추가하여 뷰에서 사용 가능하게 함
        return "usr/challenge/challengeContents";  // 카드 뉴스 내용 뷰 반환
    }
}
package com.healthFood.lab.spring2502_heathFood.controller;


import com.healthFood.lab.spring2502_heathFood.service.CardNewsService;
import com.healthFood.lab.spring2502_heathFood.vo.CardNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UsrCardNewsController {
    @Autowired
    private CardNewsService cardNewsService;

    // 카드 뉴스 목록 조회
    @RequestMapping("/usr/cardnews/cardNewsList")
    public String showCardNewsList(Model model) {
        List<CardNewsVo> cardNewsList = cardNewsService.getAllCardNews();
        model.addAttribute("cardNewsList", cardNewsService.getAllCardNews());
        return "usr/cardnews/cardNewsList"; // 카드 뉴스 목록 뷰
    }

    @RequestMapping("/usr/cardnews/cardNewsContents/{cIdx}")
    public String showCardNewsContents(@PathVariable int cIdx, Model model) {
        System.out.println("cIdx: " + cIdx);  // 로그로 확인
        CardNewsVo cardNews = cardNewsService.getCardNewsById(cIdx);
        model.addAttribute("cardNews", cardNews);  // 카드 뉴스 정보 모델에 추가
        model.addAttribute("cIdx", cIdx);  // cIdx를 모델에 추가하여 뷰에서 사용 가능하게 함
        return "usr/cardnews/cardNewsContents";  // 카드 뉴스 내용 뷰 반환
    }
}

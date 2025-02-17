package com.healthFood.lab.spring2502_heathFood.controller;

import com.healthFood.lab.spring2502_heathFood.service.CardNewsService;
import com.healthFood.lab.spring2502_heathFood.vo.CardNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminCardNewsController {

    @Autowired
    private CardNewsService cardNewsService;

    // 카드 뉴스 목록 조회
    @RequestMapping("/adm/cardnews/cardNewsList")
    public String showCardNewsList(Model model) {
        List<CardNewsVo> cardNewsList = cardNewsService.getAllCardNews();
        model.addAttribute("cardNewsList", cardNewsService.getAllCardNews());
        return "adm/cardnews/cardNewsList"; // 카드 뉴스 목록 뷰
    }

    @RequestMapping("/adm/cardnews/cardNewsContents/{cIdx}")
    public String showCardNewsContents(@PathVariable int cIdx, Model model) {
        System.out.println("cIdx: " + cIdx);  // 로그로 확인
        CardNewsVo cardNews = cardNewsService.getCardNewsById(cIdx);
        model.addAttribute("cardNews", cardNews);  // 카드 뉴스 정보 모델에 추가
        model.addAttribute("cIdx", cIdx);  // cIdx를 모델에 추가하여 뷰에서 사용 가능하게 함
        return "adm/cardnews/cardNewsContents";  // 카드 뉴스 내용 뷰 반환
    }


    // 카드 뉴스 등록 폼
    @RequestMapping("/adm/cardnews/cardNewsWrite")
    public String showCardNewsWriteForm() {
        return "adm/cardnews/cardNewsWrite"; // 카드 뉴스 작성 뷰
    }

    @PostMapping("/adm/cardnews/cardNewsWriteAction")
    public String insertCardNews(@ModelAttribute CardNewsVo cardNewsVo) {
        MultipartFile file = cardNewsVo.getUploadFile();

        if (!file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                cardNewsVo.setCnImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                cardNewsVo.setCnFilename(file.getOriginalFilename()); // 원본 파일명 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 저장 (파일 경로 및 파일명 포함)
        cardNewsService.insertCardNews(cardNewsVo);

        return "redirect:/adm/cardnews/cardNewsList";
    }


    // 카드 뉴스 수정 폼
    @RequestMapping("/adm/cardnews/cardNewsModify/{cIdx}")
    public String showCardNewsEditForm(@PathVariable int cIdx, Model model) {
        CardNewsVo cardNews = cardNewsService.getCardNewsById(cIdx);
        model.addAttribute("cardNews", cardNews);
        return "adm/cardnews/cardNewsModify"; // 카드 뉴스 수정 뷰
    }

    @PostMapping("/adm/cardnews/cardNewsModifyAction")
    public String updateCardNews(@ModelAttribute CardNewsVo cardNewsVo) {

        MultipartFile file = cardNewsVo.getUploadFile();
        // 기존 카드 뉴스 데이터 가져오기
        CardNewsVo existingCardNews = cardNewsService.getCardNewsById(cardNewsVo.getCIdx());

        // 기존 데이터가 없으면 수정 불가, 목록으로 리다이렉트
        if (existingCardNews == null) {
            return "redirect:/adm/cardnews/cardNewsList";
        }

        // 기존 데이터 업데이트
        existingCardNews.setCnTitle(cardNewsVo.getCnTitle());  // 제목 수정

        // 파일이 업로드되지 않았다면 기존 이미지 경로를 사용
        if (file != null && !file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                System.out.println("파일 저장 위치 : " + saveFile.getAbsolutePath());
                existingCardNews.setCnImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                existingCardNews.setCnFilename(fileName); // 업로드된 파일명으로 변경
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 업데이트
        cardNewsService.updateCardNews(existingCardNews);

        return "redirect:/adm/cardnews/cardNewsList";  // 수정 후 목록으로 리다이렉트
    }

//    // 카드 뉴스 수정 처리
//    @PostMapping("/adm/cardnews/cardNewsModifyAction")
//    public String updateCardNews(CardNewsVo cardNews) {
//        cardNewsService.updateCardNews(cardNews);
//        return "redirect:/adm/cardnews/cardModifyList"; // 수정 후 카드 뉴스 목록으로 리다이렉트
//    }

    //    // 카드 뉴스 삭제
//    @RequestMapping("/adm/cardnews/cardNewsDelete/{cIdx}")
//    public String deleteCardNews(@PathVariable int cIdx) {
//        cardNewsService.deleteCardNews(cIdx);
//        return "redirect:/adm/cardnews/cardNewsList"; // 삭제 후 카드 뉴스 목록으로 리다이렉트
//    }
    @PostMapping("/adm/cardnews/cardNewsDelete/{cIdx}")
    public String deleteCardNews(@PathVariable int cIdx) {
        cardNewsService.deleteCardNews(cIdx);  // 해당 게시글 삭제
        return "redirect:/adm/cardnews/cardNewsList";  // 삭제 후 목록으로 리다이렉트
    }


//    @PostMapping("/UploadImage")
//    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("uploadFile") MultipartFile file) {
//        Map<String, Object> response = new HashMap<>();
//
//        if (file != null && !file.isEmpty()) {
//            try {
//                // 업로드할 디렉토리와 파일명 설정
//                String uploadDir = "C:/UploadImage/";
//                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//                File saveFile = new File(uploadDir + fileName);
//                file.transferTo(saveFile);  // 파일 저장
//
//                response.put("success", true);
//                response.put("filename", fileName);  // 파일명을 반환
//            } catch (IOException e) {
//                e.printStackTrace();
//                response.put("success", false);
//            }
//        } else {
//            response.put("success", false);
//        }
//
//        return ResponseEntity.ok(response);
//    }
}



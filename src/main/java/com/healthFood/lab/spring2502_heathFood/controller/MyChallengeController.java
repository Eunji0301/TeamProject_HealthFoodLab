package com.healthFood.lab.spring2502_heathFood.controller;

import com.healthFood.lab.spring2502_heathFood.service.MyChallengeService;
import com.healthFood.lab.spring2502_heathFood.vo.MyChallengeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MyChallengeController {

    @Autowired
    private MyChallengeService myChallengeService;

//    @RequestMapping("/usr/myChallenge/myChallengeList")
//    public String showMyChallengeList() {
//        return "usr/myChallenge/myChallengeList";
//    }

    @RequestMapping("/usr/myChallenge/myChallengeList")
    public String showMyChallengeList(Model model) {
        List<MyChallengeVo> myChallengeList = myChallengeService.getAllMyChallenges();
        model.addAttribute("myChallengeList", myChallengeService.getAllMyChallenges());
        return "usr/myChallenge/myChallengeList"; // 마이 챌린지 목록 뷰
    }

//    @RequestMapping("/usr/myChallenge/myChallengeWrite")
//    public String showMyChallengeWrite() {
//        return "usr/myChallenge/myChallengeWrite";
//    }

    @RequestMapping("/usr/myChallenge/myChallengeWrite")
    public String showMyChallengeWriteForm() {
        return "usr/myChallenge/myChallengeWrite";
    }

    @PostMapping("/usr/myChallenge/myChallengeWriteAction")
    public String insertMyChallenge(@ModelAttribute MyChallengeVo myChallengeVo) {
        MultipartFile file = myChallengeVo.getUploadFile();

        if (!file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                myChallengeVo.setUccImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                myChallengeVo.setUccFilename(file.getOriginalFilename()); // 원본 파일명 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 저장 (파일 경로 및 파일명 포함)
        myChallengeService.insertMyChallenge(myChallengeVo);

        return "redirect:/usr/myChallenge/myChallengeList";
    }

//    @RequestMapping("/usr/myChallenge/myChallengeModify")
//    public String showMyChallengeModify() {
//        return "usr/myChallenge/myChallengeModify";
//    }

    @RequestMapping("/usr/myChallenge/myChallengeModify")
    public String showMyChallengeModifyForm(@RequestParam(value = "uccIdx", required = false, defaultValue = "1") int uccIdx, Model model) {
        System.out.println("받은 uccIdx: " + uccIdx); // 로그 출력

        MyChallengeVo myChallenge = myChallengeService.getMyChallengeById(uccIdx);

        // 데이터가 없을 경우 기본적으로 목록으로 리디렉트
        if (myChallenge == null) {
            System.out.println("uccIdx=1 데이터 없음. 목록으로 이동");
            return "redirect:/usr/myChallenge/myChallengeList";
        }

        model.addAttribute("myChallenge", myChallenge);
        return "usr/myChallenge/myChallengeModify"; // 수정 페이지 반환
    }


    @PostMapping("/usr/myChallenge/myChallengeModifyAction")
    public String updateMyChallenge(@ModelAttribute MyChallengeVo myChallengeVo) {
        MultipartFile file = myChallengeVo.getUploadFile();
        // 기존 챌린지 데이터 가져오기
        MyChallengeVo existingMyChallenge = myChallengeService.getMyChallengeById(myChallengeVo.getUccIdx());

        // 기존 데이터가 없으면 수정 불가, 목록으로 리다이렉트
        if (existingMyChallenge == null) {
            return "redirect:/usr/myChallenge/myChallengeList";
        }

        // 기존 데이터 업데이트
        existingMyChallenge.setUccTitle(myChallengeVo.getUccTitle());  // 제목 수정
        existingMyChallenge.setUccContents(myChallengeVo.getUccContents());  // 내용 수정
        // existingMyChallenge.setCiDuration(challengeVo.getCiDuration());  // 기간 수정

        // 파일이 업로드되었을 경우만 처리
        if (file != null && !file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                System.out.println("파일 저장 위치 : " + saveFile.getAbsolutePath());
                existingMyChallenge.setUccImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                existingMyChallenge.setUccFilename(file.getOriginalFilename()); // 원본 파일명 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 업데이트
        myChallengeService.updateMyChallenge(existingMyChallenge);
//        System.out.println("파일 경로: " + existingMyChallenge.getUccImage());

        return "redirect:/usr/myChallenge/myChallengeList";  // 수정 후 목록으로 리다이렉트
    }

    @RequestMapping("/usr/myChallenge/myChallengeDetail")
    public String showMyChallengeDetail() {
        return "usr/myChallenge/myChallengeDetail";
    }

    @RequestMapping("/usr/myChallenge/myChallengeContents")
    public String showMyChallengeContents() {
        return "usr/myChallenge/myChallengeContents";
    }

    @RequestMapping("/usr/myChallenge/myChallengeContents/{uccIdx}")
    public String showMyChallengeContents(@PathVariable int uccIdx, Model model) {
        System.out.println("uccIdx: " + uccIdx);  // 로그로 확인
        MyChallengeVo myChallenge = myChallengeService.getMyChallengeById(uccIdx);
        model.addAttribute("myChallenge", myChallenge);  // 챌린지 정보 모델에 추가
        model.addAttribute("uccIdx", uccIdx);  // uccIdx를 모델에 추가하여 뷰에서 사용 가능하게 함
        return "usr/myChallenge/myChallengeContents";  // 챌린지 내용 뷰 반환
    }


}

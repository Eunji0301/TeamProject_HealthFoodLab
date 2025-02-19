package com.healthFood.lab.spring2502_heathFood.controller;

import com.healthFood.lab.spring2502_heathFood.service.ChallengeService;
import com.healthFood.lab.spring2502_heathFood.vo.ChallengeVo;
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
public class AdminChallengeController {
    @Autowired
    private ChallengeService challengeService;

    //    @RequestMapping("/adm/challenge/challengeList")
//    public String showChallengeList() {
//        return "adm/challenge/challengeList";
//    }
// 카드 뉴스 목록 조회
    @RequestMapping("/adm/challenge/challengeList")
    public String showChallengeList(Model model) {
        List<ChallengeVo> challengeList = challengeService.getAllChallenges();
        model.addAttribute("challengeList", challengeService.getAllChallenges());
        return "adm/challenge/challengeList"; // 카드 뉴스 목록 뷰
    }

    @RequestMapping("/adm/challenge/challengeContents/{ciIdx}")
    public String showChallengeContents(@PathVariable int ciIdx, Model model) {
        System.out.println("ciIdx: " + ciIdx);  // 로그로 확인
        ChallengeVo challenge = challengeService.getChallengeById(ciIdx);
        model.addAttribute("challenge", challenge);  // 챌린지 정보 모델에 추가
        model.addAttribute("ciIdx", ciIdx);  // ciIdx를 모델에 추가하여 뷰에서 사용 가능하게 함
        return "adm/challenge/challengeContents";  // 챌린지 내용 뷰 반환
    }

    @RequestMapping("/adm/challenge/challengeModify/{ciIdx}")
    public String showChallengeModifyForm(@PathVariable int ciIdx, Model model) {
        ChallengeVo challenge = challengeService.getChallengeById(ciIdx);
        model.addAttribute("challenge", challenge);
        return "adm/challenge/challengeModify";
    }

    @RequestMapping("/adm/challenge/challengeWrite")
    public String showChallengeWriteForm() {
        return "adm/challenge/challengeWrite";
    }

    @PostMapping("/adm/challenge/challengeModifyAction")
    public String updateChallenge(@ModelAttribute ChallengeVo challengeVo) {
        MultipartFile file = challengeVo.getUploadFile();
        // 기존 챌린지 데이터 가져오기
        ChallengeVo existingChallenge = challengeService.getChallengeById(challengeVo.getCiIdx());

        // 기존 데이터가 없으면 수정 불가, 목록으로 리다이렉트
        if (existingChallenge == null) {
            return "redirect:/adm/challenge/challengeList";
        }

        // 기존 데이터 업데이트
        existingChallenge.setCiTitle(challengeVo.getCiTitle());  // 제목 수정
        existingChallenge.setCiContents(challengeVo.getCiContents());  // 내용 수정
        existingChallenge.setCiDuration(challengeVo.getCiDuration());  // 기간 수정

        // 파일이 업로드되었을 경우만 처리
        if (file != null && !file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                System.out.println("파일 저장 위치 : " + saveFile.getAbsolutePath());
                existingChallenge.setCiImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                existingChallenge.setCiFilename(file.getOriginalFilename()); // 원본 파일명 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 업데이트
        challengeService.updateChallenge(existingChallenge);
//        System.out.println("파일 경로: " + existingChallenge.getCiImage());

        return "redirect:/adm/challenge/challengeList";  // 수정 후 목록으로 리다이렉트
    }

    @PostMapping("/adm/challenge/challengeWriteAction")
    public String insertChallenge(@ModelAttribute ChallengeVo challengeVo) {
        MultipartFile file = challengeVo.getUploadFile();

        if (!file.isEmpty()) {
            String uploadDir = "C:/UploadImage/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);  // 파일 저장
                challengeVo.setCiImage("/UploadImage/" + fileName);  // DB에 저장할 경로 설정
                challengeVo.setCiFilename(file.getOriginalFilename()); // 원본 파일명 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // DB 저장 (파일 경로 및 파일명 포함)
        challengeService.insertChallenge(challengeVo);

        return "redirect:/adm/challenge/challengeList";
    }

    // 챌린지 삭제
    @RequestMapping("/adm/challenge/challengeDelete/{ciIdx}")
    public String deleteChallenge(@PathVariable int ciIdx) {
        challengeService.deleteChallenge(ciIdx);
        return "redirect:/adm/challenge/challengeList"; // 삭제 후 챌린지 목록으로 리다이렉트
    }
}
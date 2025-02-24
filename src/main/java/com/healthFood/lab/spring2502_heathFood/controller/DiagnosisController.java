package com.healthFood.lab.spring2502_heathFood.controller;

//import com.example.healingfoodlab.entity.ActivityLevel;
//import com.example.healingfoodlab.entity.LiquorType;
//import com.example.healingfoodlab.service.DiagnosisService;
//import com.example.healingfoodlab.vo.DiagnosisVO;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usr/diagnosis")
public class DiagnosisController {

    /*private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }*/


    @RequestMapping("/Diagnosis")
    public String showDiagnosis() {
        return "usr/diagnosis/Diagnosis";
    }


    @RequestMapping("/DiagnosisSelect1")
    public String showDiagnosisSelect1() {
        return "usr/diagnosis/DiagnosisSelect1";
    }


    @GetMapping("/DiagnosisResult")
    public String showDiagnosisResult() {
        return "usr/diagnosis/DiagnosisResult";
    }

    /*@GetMapping("/usr/diagnosis/DiagnosisSelect1")
    public String doDiagnosis(@RequestParam("gender") String gender,
                              @RequestParam("height") float height,
                              @RequestParam("weight") float weight,
                              @RequestParam("age") int age,
                              @RequestParam("disease") String disease,
                              @RequestParam("liquor") String liquor,
                              @RequestParam("activity") String activity,
                              @SessionAttribute(name = "uIdx", required = false) Integer userId,
                              HttpSession session
    ) {
        if (userId == null) {
            userId = (Integer) session.getAttribute("uIdx"); // 세션에서 직접 가져오기
        }

        if (userId == null) {
            return "redirect:/user/login"; // 세션에도 없으면 로그인 페이지로 리다이렉트
        }

        // DiagnosisVO 객체 생성
        DiagnosisVO diagnosisVO = new DiagnosisVO();
        diagnosisVO.setDrGender(gender);
        diagnosisVO.setDrHeight(height);
        diagnosisVO.setDrWeight(weight);
        diagnosisVO.setDrAge(age);
        diagnosisVO.setDrDisease(disease);
        diagnosisVO.setDrLiquor(liquor);
        diagnosisVO.setDrActivity(activity);
        *//*diagnosisVO.setuIdx(userId); // 로그인한 유저 ID 저장*//*

        System.out.println("diagnosisVO = " + diagnosisVO);

        // 다음 페이지로 이동
        return "redirect:/usr/diagnosis/DiagnosisSelect2_1";
    }*/

    /*@PostMapping("/save")
    public String doDiagnosis(@RequestBody DiagnosisVO diagnosisVO) {
        diagnosisService.saveDiagnosis(diagnosisVO);

        System.out.println("저장됨?");


        return "진단 데이터 저장 완료";

    }*/

    /*@PostMapping("/submit")
    public ResponseEntity<String> submitDiagnosis(@RequestBody DiagnosisVO diagnosisVO) {
        System.out.println("Received Diagnosis Data: " + diagnosisVO);

        // 진단 데이터 저장
        diagnosisService.saveDiagnosis(diagnosisVO);

        return ResponseEntity.ok("진단 저장 완료!");
    }*/



}

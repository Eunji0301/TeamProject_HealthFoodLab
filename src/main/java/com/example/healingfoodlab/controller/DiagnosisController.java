package com.example.healingfoodlab.controller;

import com.example.healingfoodlab.entity.ActivityLevel;
import com.example.healingfoodlab.entity.LiquorType;
import com.example.healingfoodlab.service.MealRecordService;
import com.example.healingfoodlab.vo.DiagnosisVO;
import com.example.healingfoodlab.vo.MealRecordVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.healingfoodlab.service.DiagnosisService;
import com.example.healingfoodlab.vo.DiagnosisVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequestMapping("/usr/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final MealRecordService mealRecordService;

    public DiagnosisController(DiagnosisService diagnosisService, MealRecordService mealRecordService) {
        this.diagnosisService = diagnosisService;
        this.mealRecordService = mealRecordService;
    }


    @RequestMapping("/Diagnosis")
    public String showDiagnosis() {
        return "usr/diagnosis/Diagnosis";
    }


    @GetMapping("/DiagnosisSelect1")
    public String showDiagnosisForm(Model model) {
        // diagnosisVO 객체를 생성하여 모델에 추가
        model.addAttribute("diagnosisVO", new DiagnosisVO());
        return "usr/diagnosis/DiagnosisSelect1";
    }

    @PostMapping("/submit")
    public String submitDiagnosis(@ModelAttribute DiagnosisVO diagnosisVO, RedirectAttributes redirectAttributes) {
        // 저장 또는 로직 처리
        System.out.println("받은 데이터: " + diagnosisVO);
        System.out.println("받은 drHeight: " + diagnosisVO.getDrHeight());
        System.out.println("받은 drWeight: " + diagnosisVO.getDrWeight());
        System.out.println("받은 drAge: " + diagnosisVO.getDrAge());
        System.out.println("받은 drDisease: " + diagnosisVO.getDrDisease());
        System.out.println("받은 drLiquor: " + diagnosisVO.getDrLiquor());
        System.out.println("받은 drActivity: " + diagnosisVO.getDrActivity());
        System.out.println("받은 drGender: " + diagnosisVO.getDrGender());


        // 처리 후 리다이렉트
        redirectAttributes.addFlashAttribute("message", "진단 정보가 저장되었습니다.");
        return "redirect:/usr/diagnosis/DiagnosisSelect2_1";
    }

    @GetMapping("/DiagnosisSelect2_1")
    public String showDiagnosisSelect2_1() {
        return "usr/diagnosis/DiagnosisSelect2_1";
    }

    @GetMapping("/DiagnosisSelect2_2")
    public String showDiagnosisSelect2_2() {
        return "usr/diagnosis/DiagnosisSelect2_2";
    }

    @GetMapping("/DiagnosisSelect2_3")
    public String showDiagnosisSelect2_3() {
        return "usr/diagnosis/DiagnosisSelect2_3";
    }

    // ✅ 최종 식단 데이터 서버 전송 (2_3 페이지에서 실행됨)
    @PostMapping("/submitAllMeal")
    public ResponseEntity<HashMap<String, Object>> submitAllMeal(@RequestBody MealRecordVo mealRecordVo, HttpSession session) {
        System.out.println("🚀 [서버에서 받은 데이터] " + mealRecordVo);

        HashMap<String, Object> response = new HashMap<>();

        if (mealRecordVo == null) {
            System.err.println("❌ mealRecordVo is null! (JSON 매핑 오류 가능성)");
            response.put("status", "fail");
            response.put("message", "❌ JSON 데이터가 null입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        System.out.println("📌 받은 JSON 데이터: + ");
        System.out.println("🍔 mCalorie: " + mealRecordVo.getMCalorie());
        System.out.println("🥦 mCarb: " + mealRecordVo.getMCarb());
        System.out.println("🍖 mProtein: " + mealRecordVo.getMProtein());
        System.out.println("🧂 mSodium: " + mealRecordVo.getMSodium());

        if (mealRecordVo.getMCalorie() == 0) {
            System.err.println("❌ mCalorie 값이 0입니다. 잘못된 데이터가 전송됨.");
            response.put("status", "fail");
            response.put("message", "❌ mCalorie 값이 0입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 🟢 DB 저장
        mealRecordService.saveMealRecord(mealRecordVo);

        // ✅ 응답을 HashMap 형태로 변환
        response.put("status", "success");
        response.put("message", "✅ 식사 기록이 성공적으로 저장되었습니다.");
        response.put("data", mealRecordVo); // MealRecordVo 객체를 JSON 데이터로 포함 가능

        return ResponseEntity.ok(response);
    }



    @GetMapping("/DiagnosisResult")
    public String showDiagnosisResult() {
        return "usr/diagnosis/DiagnosisResult";
    }



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

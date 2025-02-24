package com.healthFood.lab.spring2502_heathFood.controller;

import com.example.healingfoodlab.repository.DiagnosisRepository;
import com.example.healingfoodlab.service.MealRecordService;
import com.example.healingfoodlab.vo.DiagnosisResultVO;
import com.example.healingfoodlab.vo.DiagnosisVO;
import com.example.healingfoodlab.vo.MealRecordVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.healingfoodlab.service.DiagnosisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/usr/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final MealRecordService mealRecordService;
    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisController(DiagnosisService diagnosisService, MealRecordService mealRecordService, DiagnosisRepository diagnosisRepository) {
        this.diagnosisService = diagnosisService;
        this.mealRecordService = mealRecordService;
        this.diagnosisRepository = diagnosisRepository;
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
        // 받은 데이터를 출력
        System.out.println("받은 데이터: " + diagnosisVO);
        System.out.println("받은 drHeight: " + diagnosisVO.getDrHeight());
        System.out.println("받은 drWeight: " + diagnosisVO.getDrWeight());
        System.out.println("받은 drAge: " + diagnosisVO.getDrAge());
        System.out.println("받은 drDisease: " + diagnosisVO.getDrDisease());
        System.out.println("받은 drLiquor: " + diagnosisVO.getDrLiquor());
        System.out.println("받은 drActivity: " + diagnosisVO.getDrActivity());
        System.out.println("받은 drGender: " + diagnosisVO.getDrGender());

        // DiagnosisVO 객체 저장
        diagnosisRepository.insertDiagnosis(diagnosisVO);  // 해당 리포지토리의 save() 메서드를 호출하여 데이터 저장

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
    public ResponseEntity<HashMap<String, Object>> submitAllMeal(@RequestBody MealRecordVo mealRecordVo) {

        HashMap<String, Object> response = new HashMap<>();

        if (mealRecordVo == null) {
            System.err.println("❌ mealRecordVo is null! JSON 매핑 오류 가능성");
            response.put("status", "fail");
            response.put("message", "❌ JSON 데이터가 null입니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 🔍 모든 필드 값 출력 (디버깅)
        System.out.println("📌 받은 JSON 데이터:");
        System.out.println("🍔 mCalorie: " + mealRecordVo.getMCalorie());
        System.out.println("🥦 mCarb: " + mealRecordVo.getMCarb());
        System.out.println("🍖 mProtein: " + mealRecordVo.getMProtein());
        System.out.println("🧂 mSodium: " + mealRecordVo.getMSodium());

        if (mealRecordVo.getMCalorie().equals("0.0")) {
            System.err.println("❌ mCalorie 값이 0.0입니다. 잘못된 데이터가 전송됨.");
            response.put("status", "fail");
            response.put("message", "❌ mCalorie 값이 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (mealRecordVo.getUIdx() == null) {
            System.out.println("⚠ uIdx가 null이므로 기본값 1로 설정합니다.");
            mealRecordVo.setUIdx(1);
        }

        System.out.println("📌 최종 저장될 MealRecordVo: " + mealRecordVo);

        mealRecordService.saveMealRecord(mealRecordVo);

        response.put("status", "success");
        response.put("message", "✅ 식사 기록이 성공적으로 저장되었습니다.");
        response.put("data", mealRecordVo);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/analyzeNutrition")
    public ResponseEntity<Map<String, Object>> analyzeNutrition(@RequestParam(defaultValue = "1") int uIdx) {
        Map<String, Object> response = new HashMap<>();

        try {
            DiagnosisResultVO result = diagnosisService.analyzeNutrition(uIdx);
            response.put("status", "success");
            response.put("message", "✅ 분석 완료");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "❌ 분석 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @PostMapping("/DiagnosisResult")
    public String showDiagnosisResult(@RequestParam(defaultValue = "1") int uIdx, Model model) {
        DiagnosisResultVO result = diagnosisService.analyzeNutrition(uIdx);
        model.addAttribute("result", result);
        return "usr/diagnosis/DiagnosisResult";
    }

   /* @GetMapping("/DiagnosisResult")
    public String showDiagnosisResult() {
        DiagnosisResultVO result = diagnosisService.analyzeNutrition(uIdx);
        model.addAttribute("result", result);
        return "usr/diagnosis/DiagnosisResult";
    }*/




}
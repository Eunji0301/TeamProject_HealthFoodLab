package com.healthFood.lab.spring2502_heathFood.controller;



//*import com.example.healingfoodlab.service.MealRecordService;
//import com.example.healingfoodlab.vo.MealRecordVo;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usr/meal")
public class MealRecordController {

   /* private final MealRecordService mealRecordService;

    public MealRecordController(MealRecordService mealRecordService) {
        this.mealRecordService = mealRecordService;
    }*/

    // 🍚 DiagnosisSelect2_1 화면 띄우기 + session 저장 API
    @GetMapping("/DiagnosisSelect2_1")
    public String showDiagnosisSelect2_1() {
        return "usr/diagnosis/DiagnosisSelect2_1";
    }

    // 🍞 DiagnosisSelect2_2 화면 띄우기 + session 저장 API
    @GetMapping("/DiagnosisSelect2_2")
    public String showDiagnosisSelect2_2() {
        return "usr/diagnosis/DiagnosisSelect2_2";
    }


    // 🥗 DiagnosisSelect2_3 화면 띄우기 + session 저장 API
    @GetMapping("/DiagnosisSelect2_3")
    public String showDiagnosisSelect2_3() {
        return "usr/diagnosis/DiagnosisSelect2_3";
    }

   /* @PostMapping("/saveMealTemp")
    public ResponseEntity<Map<String, String>> saveMealTemp(MealRecordVo mealRecordVo, HttpSession session) {
        List<MealRecordVo> mealRecords = (List<MealRecordVo>) session.getAttribute("mealRecords");

        if (mealRecords == null) {
            mealRecords = new ArrayList<>();
        }

        mealRecords.add(mealRecordVo);
        session.setAttribute("mealRecords", mealRecords);
        System.out.println("✅ 받은 데이터: " + mealRecordVo);

        System.out.println("✅ 현재 세션에 저장된 mealRecords: " + mealRecords);

        return ResponseEntity.ok(Map.of("status", "success", "message", "현재 페이지 데이터 저장 완료!"));
    }*/




/*
    @PostMapping("/saveAllMeals")
    public ResponseEntity<String> saveAllMeals(HttpSession session) {
        List<MealRecordVo> mealRecords = (List<MealRecordVo>) session.getAttribute("mealRecords");

        if (mealRecords == null || mealRecords.isEmpty()) {
            System.out.println("❌ 저장할 데이터 없음!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("저장할 데이터가 없습니다.");
        }

        System.out.println("✅ 최종 저장되는 mealRecords: " + mealRecords);

        for (MealRecordVo record : mealRecords) {
            System.out.println("🔥 DB에 저장되는 데이터: " + record);
            mealRecordService.saveMealRecord(record);
        }

        session.removeAttribute("mealRecords");

        return ResponseEntity.ok("모든 Meal Record 저장 완료!");
    }*/



}

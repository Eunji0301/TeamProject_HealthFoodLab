

package com.example.healingfoodlab.controller;

import com.example.healingfoodlab.service.MealRecordService;
import com.example.healingfoodlab.vo.MealRecordVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/meal")
public class MealRecordApiController {

    private final MealRecordService mealRecordService;

    public MealRecordApiController(MealRecordService mealRecordService) {
        this.mealRecordService = mealRecordService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitMeal(@RequestBody MealRecordVo mealRecordVo, HttpSession session) {
        System.out.println("🚀 수신한 JSON 데이터: " + mealRecordVo);  // 🔥 JSON 디버깅 추가
        System.out.println("🔎 입력된 mCalorie: " + mealRecordVo.getMCalorie());
        System.out.println("🔎 입력된 mCarb: " + mealRecordVo.getMCarb());
        System.out.println("🔎 입력된 mSodium: " + mealRecordVo.getMSodium());

       /* if (mealRecordVo == null || mealRecordVo.getMCalorie() == 0) {
            System.err.println("❌ 서버에서 받은 데이터가 null이거나 0입니다!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터가 올바르지 않습니다.");
        }*/

        // 🟢 세션에서 기존 데이터 가져오기
        List<MealRecordVo> mealRecords = (List<MealRecordVo>) session.getAttribute("mealRecords");
        if (mealRecords == null) {
            mealRecords = new ArrayList<>();
        }

        mealRecords.add(mealRecordVo);
        session.setAttribute("mealRecords", mealRecords);

        System.out.println("🚀 세션에 저장된 mealRecords: " + mealRecords);

        // 🟢 DB 저장
        mealRecordService.saveMealRecord(mealRecordVo);

        return ResponseEntity.ok("식사 기록 저장 완료");
    }



}


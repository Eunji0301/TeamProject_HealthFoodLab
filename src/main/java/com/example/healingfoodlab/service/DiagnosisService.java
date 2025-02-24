
package com.example.healingfoodlab.service;
import com.example.healingfoodlab.repository.*;
import com.example.healingfoodlab.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final MealRecordRepository mealRecordRepository;
    private final NutritionStandardRepository nutritionStandardRepository;
    private final GradeStandardRepository gradeStandardRepository;
    private final DiagnosisResultRepository diagnosisResultRepository;
    private final MealRecordService mealRecordService;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository, MealRecordRepository mealRecordRepository,
                            NutritionStandardRepository nutritionStandardRepository, GradeStandardRepository gradeStandardRepository,
                            DiagnosisResultRepository diagnosisResultRepository, MealRecordService mealRecordService) {
        this.diagnosisRepository = diagnosisRepository;
        this.mealRecordRepository = mealRecordRepository;
        this.nutritionStandardRepository = nutritionStandardRepository;
        this.gradeStandardRepository = gradeStandardRepository;
        this.diagnosisResultRepository = diagnosisResultRepository;
        this.mealRecordService = mealRecordService;
    }

    /**
     * 유저의 식사 데이터와 기준 데이터를 비교하여 건강 평가를 수행하고 결과를 저장
     */
    @Transactional
    public DiagnosisResultVO analyzeNutrition(int uIdx) {
        // 1️⃣ 유저의 최근 영양 및 생활패턴 데이터 가져오기
        DiagnosisVO diagnosis = diagnosisRepository.findLatestByUserId(uIdx);
        /*if (diagnosis == null) {
            throw new IllegalArgumentException("❌ 영양 및 생활패턴 데이터가 없습니다.");
        }*/

        // 2️⃣ 유저의 최근 식사 기록 가져오기
        MealRecordVo mealRecord = mealRecordRepository.findLatestByUserId(uIdx);

        // 2.1️⃣ 만약 식사 기록이 없다면, 새로운 MealRecord 저장
        if (mealRecord == null) {
            mealRecord = new MealRecordVo();
            mealRecord.setUIdx(uIdx);
            mealRecord.setMCalorie("0.0");
            mealRecord.setMCarb("0.0");
            mealRecord.setMProtein("0.0");
            mealRecord.setMFat("0.0");
            mealRecord.setMSugar("0.0");
            mealRecord.setMFiber("0.0");
            mealRecord.setMCholesterol("0.0");
            mealRecord.setMSodium("0.0");

            // 저장 (DB에 저장)
            mealRecordService.saveMealRecord(mealRecord);
        }

        // 3️⃣ 기준 영양소 데이터 가져오기 (100g 기준)
        List<NutritionStandardVO> nutritionStandards = nutritionStandardRepository.findAll();
        if (nutritionStandards.isEmpty()) {
            throw new IllegalStateException("❌ 영양소 기준 데이터가 없습니다.");
        }

        // 4️⃣ 총 섭취량을 기준 데이터와 비교하여 점수 계산
        double totalScore = 100.0;
        String riskEvaluation = "";
        Map<String, Double> nutritionComparison = new HashMap<>();

        for (NutritionStandardVO standard : nutritionStandards) {
            double intakeValue = getMealIntakeByNutrient(mealRecord, standard.getNsName());
            double recommendedValue = Double.parseDouble(standard.getNsCalorie()); // 기준 데이터 (예시: kcal)

            double percentage = (intakeValue / recommendedValue) * 100;
            nutritionComparison.put(standard.getNsName(), percentage);

            // 기준 대비 과다 섭취 시 감점
            if (percentage > 150) {
                totalScore -= 10;
                riskEvaluation += "⚠ " + standard.getNsName() + " 섭취량이 기준 대비 과다합니다.\n";
            } else if (percentage < 50) {
                totalScore -= 5;
                riskEvaluation += "⚠ " + standard.getNsName() + " 섭취량이 기준 대비 부족합니다.\n";
            }
        }

        // 5️⃣ 결과 등급 결정 (GRADE_STANDARD 활용)
        GradeStandardVO grade = gradeStandardRepository.findByScoreRange(totalScore);

        // 6️⃣ 결과 저장
        DiagnosisResultVO diagnosisResult = new DiagnosisResultVO();
        diagnosisResult.setUIdx(uIdx);
        diagnosisResult.setDrIdx(diagnosis.getDrIdx());
        diagnosisResult.setMIdx(mealRecord.getMidx());
        diagnosisResult.setDrTotalCalorie(mealRecord.getMCalorie());
        diagnosisResult.setDrTotalCarb(mealRecord.getMCarb());
        diagnosisResult.setDrTotalProtein(mealRecord.getMProtein());
        diagnosisResult.setDrTotalFat(mealRecord.getMFat());
        diagnosisResult.setDrTotalSugar(mealRecord.getMSugar());
        diagnosisResult.setDrTotalFiber(mealRecord.getMFiber());
        diagnosisResult.setDrTotalCholesterol(mealRecord.getMCholesterol());
        diagnosisResult.setDrTotalSodium(mealRecord.getMSodium());
        diagnosisResult.setDrRiskEvaluation(riskEvaluation);
        diagnosisResult.setDrPersonalRecommendation("👉 균형 잡힌 식단을 위해 특정 영양소를 조절하세요.");
        diagnosisResult.setDrTCreateAt(new Timestamp(System.currentTimeMillis()));

        diagnosisResultRepository.saveDiagnosisResult(diagnosisResult);

        return diagnosisResult;
    }

    /**
     * 특정 영양소 섭취량 반환 (식사 기록에서 데이터 추출)
     */
    private double getMealIntakeByNutrient(MealRecordVo mealRecord, String nutrient) {
        switch (nutrient) {
            case "칼로리":
                return Double.parseDouble(mealRecord.getMCalorie());
            case "탄수화물":
                return Double.parseDouble(mealRecord.getMCarb());
            case "단백질":
                return Double.parseDouble(mealRecord.getMProtein());
            case "지방":
                return Double.parseDouble(mealRecord.getMFat());
            case "당":
                return Double.parseDouble(mealRecord.getMSugar());
            case "식이섬유":
                return Double.parseDouble(mealRecord.getMFiber());
            case "콜레스테롤":
                return Double.parseDouble(mealRecord.getMCholesterol());
            case "나트륨":
                return Double.parseDouble(mealRecord.getMSodium());
            default:
                return 0.0;
        }
    }
}



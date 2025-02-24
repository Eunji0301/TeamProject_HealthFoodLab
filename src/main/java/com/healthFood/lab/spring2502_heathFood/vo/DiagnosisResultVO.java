package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiagnosisResultVO {

    // 해당 정보 인덱스
    private int drTIdx;

    // 유저인덱스
    private int uIdx;

    // 영양 및 생활패턴 인덱스
    private int drIdx;

    // 유저가 입력한 영양성분 정보 인덱스
    private int mIdx;

    // 유저 신장 정보(cm)
    private String drTHeight;

    // 유저 체중 정보(kg)
    private String drTWeight;

    // 유저 나이 정보
    private String drTAge;

    // 유저 의심질환 정보
    private String drTDisease;

    // 총 섭취 칼로리 (kcal)
    private String drTotalCalorie;

    // 총 탄수화물 섭취량 (g)
    private String drTotalCarb;

    // 총 단백질 섭취량 (g)
    private String drTotalProtein;

    // 총 지방 섭취량 (g)
    private String drTotalFat;

    // 총 당 섭취량 (g)
    private String drTotalSugar;

    // 총 식이섬유 섭취량 (g)
    private String drTotalFiber;

    // 총 콜레스테롤 섭취량 (mg)
    private String drTotalCholesterol;

    // 총 나트륨 섭취량 (mg)
    private String drTotalSodium;

    // 건강 위험 평가
    private String drRiskEvaluation;

    // 개인 맞춤 건강 추천
    private String drPersonalRecommendation;

    // 진단 결과 생성 날짜
    private Timestamp drTCreateAt;
}

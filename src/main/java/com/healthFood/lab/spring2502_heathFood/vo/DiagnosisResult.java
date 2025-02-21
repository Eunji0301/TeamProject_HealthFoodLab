package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisResult {
    private int drTIdx;
    private String drTHeight;
    private String drTWeight;
    private String drTAge;
    private String drTDisease;
    private String drTotalCalorie;
    private String drTotalCarb;
    private String drTotalProtein;
    private String drTotalFat;
    private String drTotalSugar;
    private String drTotalFiber;
    private String drTotalCholesterol;
    private String drTotalSodium;
    private String drRiskEvaluation;
    private String drPersonalRecommendation;
    private String  uPIPAgree;
    private String drTCreateAt;
    private int uIdx;
    private int drIdx;
    private int mIdx;
}













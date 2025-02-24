package com.example.healingfoodlab.vo;


public class GradeStandard {
    private int nsIdx;             // 영양 기준 인덱스
    private double gsMinPercentage;  // 최소 점수 퍼센트
    private double gsMaxPercentage;  // 최대 점수 퍼센트
    private String gsGrade;         // 등급 (예: A, B, C 등)
    private String gsDescription;   // 등급 설명
    private String gsColorCode;     // 색상 코드 (등급에 맞는 색)

    // Getters and Setters
    public int getNsIdx() {
        return nsIdx;
    }

    public void setNsIdx(int nsIdx) {
        this.nsIdx = nsIdx;
    }

    public double getGsMinPercentage() {
        return gsMinPercentage;
    }

    public void setGsMinPercentage(double gsMinPercentage) {
        this.gsMinPercentage = gsMinPercentage;
    }

    public double getGsMaxPercentage() {
        return gsMaxPercentage;
    }

    public void setGsMaxPercentage(double gsMaxPercentage) {
        this.gsMaxPercentage = gsMaxPercentage;
    }

    public String getGsGrade() {
        return gsGrade;
    }

    public void setGsGrade(String gsGrade) {
        this.gsGrade = gsGrade;
    }

    public String getGsDescription() {
        return gsDescription;
    }

    public void setGsDescription(String gsDescription) {
        this.gsDescription = gsDescription;
    }

    public String getGsColorCode() {
        return gsColorCode;
    }

    public void setGsColorCode(String gsColorCode) {
        this.gsColorCode = gsColorCode;
    }
}

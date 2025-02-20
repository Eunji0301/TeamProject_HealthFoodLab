package com.example.healingfoodlab.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealRecordVo {
    private int mIdx;
    private String mWriteDate;
    private double mCalorie;  // 🔥 float → double 변경
    private double mCarb;
    private double mProtein;
    private double mFat;
    private double mSugar;
    private double mFiber;
    private double mCholesterol;
    private double mSodium;
    private int uIdx;


}

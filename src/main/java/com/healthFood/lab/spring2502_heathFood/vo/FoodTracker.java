package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodTracker {
    private int ftIdx;
    private String ftWriteDate;
    private String ftMealTime;
    private String ftFoodName;
    private String ftFoodQuantity;
    private String ftFoodPortion;
    private String ftCalorie;
    private String ftCarb;
    private String ftProtein;
    private String ftFat;
    private String ftSugar;
    private String ftSodium;
    private String ftCreateAt ;
    private int uIdx;


    public FoodTracker(String ftWriteDate, String ftMealTime, String ftFoodName, String ftFoodQuantity,
                       String ftFoodPortion, String ftCalorie, String ftCarb, String ftProtein, String ftFat,
                       String ftSugar, String ftSodium, int uIdx) {
        this.ftWriteDate = ftWriteDate;
        this.ftMealTime = ftMealTime;
        this.ftFoodName = ftFoodName;
        this.ftFoodQuantity = ftFoodQuantity;
        this.ftFoodPortion = ftFoodPortion;
        this.ftCalorie = ftCalorie;
        this.ftCarb = ftCarb;
        this.ftProtein = ftProtein;
        this.ftFat = ftFat;
        this.ftSugar = ftSugar;
        this.ftSodium = ftSodium;
        this.uIdx = uIdx;
    }
}

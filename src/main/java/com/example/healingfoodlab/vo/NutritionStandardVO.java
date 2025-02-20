package com.example.healingfoodlab.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NutritionStandardVO {
    private String nsName;   // 식품명
    private int nsCode;      // 식품 코드
    private int nsAmount;    // 100g 기준량
    private String nsCalorie;
    private String nsProtein;
    private String nsFat;
    private String nsCarb;
    private String nsSodium;

}

package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiagnosisVO {
    private int drIdx;
    private float drHeight;
    private float drWeight;
    private int drAge;
    private String drDisease;
    private String drLiquor;  // 변경: Enum → String
    private String drActivity; // 변경: Enum → String
    private String drCreateAt;
    private int uIdx;
    private String drGender;
}

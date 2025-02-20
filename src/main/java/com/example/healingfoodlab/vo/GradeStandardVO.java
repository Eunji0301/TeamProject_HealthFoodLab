package com.example.healingfoodlab.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeStandardVO {

    private int gsIdx;
    private double gsMinPercentage;
    private double gsMaxPercentage;
    private String gsGrade;
    private String gsDescription;
    private String gsColorCode;
    private int nsIdx;
    private int gsCreateAt;


}

package com.example.healingfoodlab.vo;

import lombok.*;

import java.util.Date;

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
    private Date gsCreateAt;

}

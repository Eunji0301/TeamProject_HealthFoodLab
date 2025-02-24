package com.example.healingfoodlab.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealRecordVo {

    @JsonProperty("uIdx")
    private Integer uIdx;
    private Integer midx;
    private String createAt;

    @JsonProperty("mCalorie")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mCalorie;

    @JsonProperty("mCarb")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mCarb;

    @JsonProperty("mProtein")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mProtein;

    @JsonProperty("mFat")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mFat;

    @JsonProperty("mSugar")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mSugar;

    @JsonProperty("mFiber")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mFiber;

    @JsonProperty("mCholesterol")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mCholesterol;

    @JsonProperty("mSodium")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##")
    private String mSodium;
}

package com.example.healingfoodlab.vo;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NutritionStandardVO {

    private Integer nsIdx;  // 적정 섭취 기준 ID
    private String nsName;  // 적정 섭취 음식명
    private Integer nsCode;  // 적정 섭취 음식코드명
    private Integer nsAmount;  // 적정 섭취 음식총량(g)
    private String nsCalorie;  // 적정 섭취 열량 (kcal)
    private String nsCarb;  // 적정 섭취 탄수화물 (g)
    private String nsProtein;  // 적정 섭취 단백질 (g)
    private String nsFat;  // 적정 섭취 지방 (g)
    private String nsSugar;  // 적정 섭취 당 (g)
    private String nsFiber;  // 적정 섭취 식이섬유 (g)
    private String nsCholesterol;  // 적정 섭취 콜레스테롤 (mg)
    private String nsSodium;  // 적정 섭취 나트륨 (mg)
    private Timestamp nsCreateAt;  // 해당 정보 생성 날짜
    private Integer uIdx;  // 유저 인덱스
    private Integer dsIdx;  // 일별 요약 인덱스 (Daily Summary 테이블과 연결)
}

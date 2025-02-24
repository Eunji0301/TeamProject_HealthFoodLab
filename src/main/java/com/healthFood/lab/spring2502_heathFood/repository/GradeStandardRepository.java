package com.healthFood.lab.spring2502_heathFood.repository;

import com.example.healingfoodlab.vo.GradeStandard;
import com.example.healingfoodlab.vo.GradeStandardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface GradeStandardRepository {

    // 특정 영양 기준에 대한 점수 조회
    @Select("SELECT * FROM grade_standard WHERE nsIdx = #{nutritionStandardId}")
    List<GradeStandard> findByNutritionStandardId(int nutritionStandardId);




        // 총 점수에 따른 등급 정보를 조회하는 메서드
        @Select("""
        SELECT * 
        FROM grade_standard 
        WHERE gsMinPercentage <= #{totalScore} 
        AND gsMaxPercentage >= #{totalScore}
    """)
        GradeStandardVO findByScoreRange(double totalScore);
    }



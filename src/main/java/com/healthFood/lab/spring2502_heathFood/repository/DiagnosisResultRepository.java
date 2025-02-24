package com.healthFood.lab.spring2502_heathFood.repository;


import com.healthFood.lab.spring2502_heathFood.vo.DiagnosisResult;
import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface DiagnosisResultRepository {


    /**
     * 진단 결과 저장
     * @param diagnosisResultVO 진단 결과 VO
     * @return 저장된 행의 수
     */
    @Insert("""
        INSERT INTO diagnosis_result 
        (uIdx, drIdx, mIdx, drTotalCalorie, drTotalCarb, drTotalProtein, 
        drTotalFat, drTotalSugar, drTotalFiber, drTotalCholesterol, drTotalSodium, 
        drRiskEvaluation, drPersonalRecommendation, drTotalScore, drGrade, 
        drGradeDescription, drGradeColor, drResultCreateAt)
        VALUES
        (#{uIdx}, #{drIdx}, #{mIdx}, #{drTotalCalorie}, #{drTotalCarb}, #{drTotalProtein},
        #{drTotalFat}, #{drTotalSugar}, #{drTotalFiber}, #{drTotalCholesterol}, #{drTotalSodium},
        #{drRiskEvaluation}, #{drPersonalRecommendation}, #{drTotalScore}, #{drGrade}, 
        #{drGradeDescription}, #{drGradeColor}, #{drResultCreateAt})
    """)
    int saveDiagnosisResult(DiagnosisResultVO diagnosisResultVO);

    /**
     * 유저별 최신 진단 결과 조회
     * @param uIdx 유저 ID
     * @return 최신 진단 결과 VO
     */
    @Select("SELECT * FROM diagnosis_result WHERE uIdx = #{uIdx} ORDER BY drResultCreateAt DESC LIMIT 1")
    DiagnosisResultVO findLatestByUserId(@Param("uIdx") int uIdx);

    /**
     * 유저별 모든 진단 결과 조회
     * @param uIdx 유저 ID
     * @return 모든 진단 결과 리스트
     */
    @Select("SELECT * FROM diagnosis_result WHERE uIdx = #{uIdx}")
    List<DiagnosisResultVO> findByUserId(@Param("uIdx") int uIdx);

    /**
     * 특정 진단 결과 조회 (예: drIdx로 조회)
     * @param drIdx 진단 결과 ID
     * @return 진단 결과 VO
     */
    @Select("SELECT * FROM diagnosis_result WHERE drIdx = #{drIdx}")
    DiagnosisResultVO findByDrIdx(@Param("drIdx") int drIdx);

}

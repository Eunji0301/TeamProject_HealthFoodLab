
package com.healthFood.lab.spring2502_heathFood.repository;


import com.example.healingfoodlab.vo.DiagnosisVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface DiagnosisRepository  {


    @Insert("""
        INSERT INTO DIAGNOSIS_RECORD (drHeight, drWeight, drAge, drDisease, drLiquor, drActivity, drGender)
        VALUES (#{drHeight}, #{drWeight}, #{drAge}, #{drDisease}, #{drLiquor}, #{drActivity}, #{drGender})
        """)
    void insertDiagnosis(DiagnosisVO diagnosisVO);

    @Select("""
        SELECT *
        FROM diagnosis_record
        WHERE uIdx = #{uIdx}
        ORDER BY drCreateAt DESC
        LIMIT 1
    """)
    DiagnosisVO findLatestByUserId(int uIdx);










}

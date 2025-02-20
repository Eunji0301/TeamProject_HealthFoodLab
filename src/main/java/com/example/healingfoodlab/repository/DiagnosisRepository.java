
package com.example.healingfoodlab.repository;


import com.example.healingfoodlab.vo.DiagnosisVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface DiagnosisRepository  {


    @Insert("""
        INSERT INTO diagnosis_record (drHeight, drWeight, drAge, drDisease, drLiquor, drActivity, drGender)
        VALUES (#{drHeight}, #{drWeight}, #{drAge}, #{drDisease}, #{drLiquor}, #{drActivity}, #{drGender})
        """)
    void insertDiagnosis(DiagnosisVO diagnosisVO);









}

package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.DiagnosisResult;
import com.healthFood.lab.spring2502_heathFood.vo.DiagnosisResultVO;
import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiagnosisResultRepository {

    @Select("SELECT * FROM DIAGNOSIS_RESULT WHERE uIdx = #{uIdx}")
    public DiagnosisResult getUserdefaultInfo(int uIdx);


}



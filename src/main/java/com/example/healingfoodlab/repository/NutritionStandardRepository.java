
package com.example.healingfoodlab.repository;


import com.example.healingfoodlab.vo.NutritionStandardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface NutritionStandardRepository {

    @Select("""
        SELECT * 
        FROM nutrition_standard
    """)
    List<NutritionStandardVO> findAll();

}

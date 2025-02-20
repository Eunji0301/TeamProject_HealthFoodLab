
package com.example.healingfoodlab.repository;


import com.example.healingfoodlab.vo.MealRecordVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MealRecordRepository {
    @Insert("""
    INSERT INTO meal_record (mCalorie, mCarb, mProtein, mFat, mSugar, mFiber, mCholesterol, mSodium, uIdx)
    VALUES (#{mCalorie}, #{mCarb}, #{mProtein}, #{mFat}, #{mSugar}, #{mFiber}, #{mCholesterol}, #{mSodium}, 
            COALESCE(#{uIdx}, 1))  -- 🟢 uIdx가 NULL이면 기본값 1 설정
""")
    void insertMealRecord(MealRecordVo mealRecordVo);

}


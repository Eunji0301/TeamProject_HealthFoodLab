
package com.example.healingfoodlab.repository;


import com.example.healingfoodlab.vo.MealRecordVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface MealRecordRepository {
    @Insert("""
    INSERT INTO meal_record (mCalorie, mCarb, mProtein, mFat, mSugar, mFiber, mCholesterol, mSodium, uIdx)
    VALUES (#{mCalorie}, #{mCarb}, #{mProtein}, #{mFat}, #{mSugar}, #{mFiber}, #{mCholesterol}, #{mSodium}, #{uIdx})  
""")
    void insertMealRecord(MealRecordVo mealRecordVo);

    @Select("""
        SELECT *
        FROM meal_record
        WHERE uIdx = #{uIdx}
        ORDER BY mWriteDate DESC
        LIMIT 1
    """)
    MealRecordVo findLatestByUserId(int uIdx);


}


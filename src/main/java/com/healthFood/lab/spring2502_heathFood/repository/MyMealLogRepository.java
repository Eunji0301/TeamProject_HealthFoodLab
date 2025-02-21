package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MyMealLogRepository {

    public void savefoodTracker(String ftMealTime, String ftWriteDate, String ftFoodName,
                                String ftFoodQuantity, String ftFoodPortion, String ftCalorie, String ftCarb,
                                String ftProtein, String ftFat, String ftSugar, String ftSodium, int uIdx);

    @Select("SELECT * FROM food_tracker " +
            "WHERE uIdx = #{uIdx} " +
            "AND ftWriteDate = #{ftWriteDate} " +
            "AND ftMealTime LIKE CONCAT('%', #{ftMealTime}, '%')")
    public List<FoodTracker> getFoodTrackerList(int uIdx, String ftWriteDate,String ftMealTime );


}



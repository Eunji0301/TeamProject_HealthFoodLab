
package com.example.healingfoodlab.service;

import com.example.healingfoodlab.repository.MealRecordRepository;
import com.example.healingfoodlab.vo.MealRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealRecordService {

    @Autowired
    MealRecordRepository mealRecordRepository;


    public MealRecordService(MealRecordRepository mealRecordRepository) {
        this.mealRecordRepository = mealRecordRepository;
    }

    public void saveMealRecord(MealRecordVo record) {
        System.out.println("✅ DB 저장 실행: " + record);
        mealRecordRepository.insertMealRecord(record);  // ✅ 실제 DB에 INSERT 실행
    }





}

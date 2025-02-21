package com.healthFood.lab.spring2502_heathFood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthFood.lab.spring2502_heathFood.repository.DiagnosisResultRepository;
import com.healthFood.lab.spring2502_heathFood.repository.EventDatesRepository;
import com.healthFood.lab.spring2502_heathFood.repository.MyMealLogRepository;
import com.healthFood.lab.spring2502_heathFood.repository.UserRepository;
import com.healthFood.lab.spring2502_heathFood.vo.DiagnosisResult;
import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;
import com.healthFood.lab.spring2502_heathFood.vo.ResultData;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MyMealLogService {

    @Autowired
    MyMealLogRepository myMealLogRepository;
    @Autowired
    EventDatesRepository eventDatesRepository;
    @Autowired
    DiagnosisResultRepository diagnosisResultRepository;

    // 생성자
    public MyMealLogService(MyMealLogRepository myMealLogRepository, EventDatesRepository eventDatesRepository, DiagnosisResultRepository diagnosisResultRepository) {
        this.myMealLogRepository = myMealLogRepository;
        this.eventDatesRepository = eventDatesRepository;
        this.diagnosisResultRepository = diagnosisResultRepository;
        // 이유는 생기는 시점 때문에 뒤에있는게 만들어지기전에 쓸려고해서 오류가 생김
    }

    //FoodTracker 객체를 Json 형태의 String으로 보냄
    public String makeFoodTrackerByJson(FoodTracker ft) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 객체를 JSON 문자열로 변환
        String jsonString = objectMapper.writeValueAsString(ft);

       // System.out.println(jsonString);
        return jsonString;
    }

    public Map<String, Object> foodDetailCalculate(@RequestBody Map<String, Object> requestData) {
        double selectedValue = Double.parseDouble(requestData.get("value").toString());
        Map<String, Integer> foodDBResultJsonOneItem = (Map<String, Integer>) requestData.get("foodDBResultJsonOneItem");

        //System.out.println("selectedValue: "+selectedValue);
        // System.out.println("foodDBResultJsonOneItem: "+foodDBResultJsonOneItem);

        String perServing = String.valueOf(foodDBResultJsonOneItem.getOrDefault("SERVING_SIZE", 0));//영양성분함량기준량
        String perServingUnit = "";
        double perSevingInt = 0;
        if(perServing.indexOf("g") > -1){
            perServingUnit="G";
            perSevingInt = 100;
        }
        if(perServing.indexOf("ml") > -1 ){
            perServingUnit="ML";
            perSevingInt = 100;
        }

        double calories = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM1", 0)));
        double carb = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM7", 0))); //탄수화물
        double protein = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM3", 0))); //단백질
        double fat = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM4", 0))); //지방
        double sugars = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM8", 0))); //당류
        double sodium = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM14", 0))); //나트륨

//        System.out.println("Calories2: "+calories);
//        System.out.println("Carb2: "+carb);
//        System.out.println("Protein2: "+protein);
//        System.out.println("Fat2: "+fat);
//        System.out.println("Sugars2: "+sugars);
//        System.out.println("Sodium2: "+sodium);

        perSevingInt = perSevingInt * selectedValue;
        calories = calories * selectedValue;
        carb = carb * selectedValue;
        protein = protein * selectedValue;
        fat = fat * selectedValue;
        sugars = sugars * selectedValue;
        sodium = sodium * selectedValue;

//        System.out.println("Calories2: "+calories);
//        System.out.println("Carb2: "+carb);
//        System.out.println("Protein2: "+protein);
//        System.out.println("Fat2: "+fat);
//        System.out.println("Sugars2: "+sugars);
//        System.out.println("Sodium2: "+sodium);

        double carbCalories = carb * 4;
        double proteinCalories = protein * 4;
        double fatCalories = fat * 9;
        double sugarsCalories = sugars * 4;
        double totalCalories = calories;

        carbCalories = Double.parseDouble(String.format("%.2f", carbCalories));
        proteinCalories = Double.parseDouble(String.format("%.2f", proteinCalories));
        fatCalories = Double.parseDouble(String.format("%.2f", fatCalories));
        sugarsCalories = Double.parseDouble(String.format("%.2f", sugarsCalories));
        totalCalories = Double.parseDouble(String.format("%.2f", totalCalories));

//        System.out.println("Calories2: "+calories);
//        System.out.println("Carb2: "+carb);
//        System.out.println("Protein2: "+protein);
//        System.out.println("Fat2: "+fat);
//        System.out.println("Sugars2: "+sugars);
//        System.out.println("Sodium2: "+sodium);
//        System.out.println("total2: "+totalCalories);

        int carbPercent = (int)((carbCalories / totalCalories) * 100);
        int proteinPercent = (int)((proteinCalories / totalCalories) * 100);
        int fatPercent = (int)((fatCalories / totalCalories) * 100);
        int sugarsPercent = (int)((sugarsCalories / totalCalories) * 100);
        int sodiumPercent = (int)((sodium / 2300) * 100); // 하루 권장량 대비 비율

        int totalPercent = carbPercent + proteinPercent + fatPercent + sugarsPercent; // 현재 합계
        int difference = 100 - totalPercent; // 부족한 퍼센트 계산 (2%)

        carbPercent += difference; // 가장 큰 탄수화물에 부족한 값 추가

//        System.out.println("perSevingInt: "+perSevingInt);
//        System.out.println("perServingUnit: "+perServingUnit);
//        System.out.println("carbPercent: "+carbPercent);
//        System.out.println("proteinPercent: "+proteinPercent);
//        System.out.println("fatPercent: "+fatPercent);
//        System.out.println("sugarsPercent: "+sugarsPercent);
//        System.out.println("sodiumPercent: "+sodiumPercent);

        Map<String, Object> result = new HashMap<>();
        result.put("perSevingInt", perSevingInt);
        result.put("perServingUnit", perServingUnit);
        result.put("calories", calories);
        result.put("carb", carbCalories);
        result.put("protein", proteinCalories);
        result.put("fat", fatCalories);
        result.put("sugars", sugarsCalories);
        result.put("sodium", sodium);
        result.put("carbPercent", carbPercent);
        result.put("proteinPercent", proteinPercent);
        result.put("fatPercent", fatPercent);
        result.put("sugarsPercent", sugarsPercent);
        result.put("sodiumPercent", sodiumPercent);

        return result;
    }

    @Transactional
    public void savefoodTracker(String ftMealTime,
                                               String ftWriteDate,
                                               String ftFoodName,
                                               String ftFoodQuantity,
                                               String ftFoodPortion,
                                               String ftCalorie,
                                               String ftCarb,
                                               String ftProtein,
                                               String ftFat,
                                               String ftSugar,
                                               String ftSodium,
                                               int uIdx) {
        myMealLogRepository.savefoodTracker(ftMealTime,ftWriteDate,ftFoodName,ftFoodQuantity,ftFoodPortion,ftCalorie,ftCarb,ftProtein,ftFat,ftSugar,ftSodium,uIdx);
        eventDatesRepository.syncEventDates();
    }

    public List<FoodTracker> getFoodTrackerById(int uIdx, String ftWriteDate,String ftMealTime) {
        return myMealLogRepository.getFoodTrackerList(uIdx, ftWriteDate, ftMealTime);
    }


    public Map<String, Object> showfoodMealLogRecordList(Map<String, Object> requestData) {
        double selectedValue = Double.parseDouble(requestData.get("value").toString());
        Map<String, Integer> foodDBResultJsonOneItem = (Map<String, Integer>) requestData.get("foodDBResultJsonOneItem");


        String perServing = String.valueOf(foodDBResultJsonOneItem.getOrDefault("SERVING_SIZE", 0));//영양성분함량기준량
        String perServingUnit = "";
        double perSevingInt = 0;
        if(perServing.indexOf("g") > -1){
            perServingUnit="G";
            perSevingInt = 100;
        }
        if(perServing.indexOf("ml") > -1 ){
            perServingUnit="ML";
            perSevingInt = 100;
        }

        double calories = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM1", 0)));
        double carb = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM7", 0))); //탄수화물
        double protein = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM3", 0))); //단백질
        double fat = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM4", 0))); //지방
        double sugars = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM8", 0))); //당류
        double sodium = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM14", 0))); //나트륨


        perSevingInt = perSevingInt * selectedValue;
        calories = calories * selectedValue;
        carb = carb * selectedValue;
        protein = protein * selectedValue;
        fat = fat * selectedValue;
        sugars = sugars * selectedValue;
        sodium = sodium * selectedValue;


        double carbCalories = carb * 4;
        double proteinCalories = protein * 4;
        double fatCalories = fat * 9;
        double sugarsCalories = sugars * 4;
        double totalCalories = calories;

        carbCalories = Double.parseDouble(String.format("%.2f", carbCalories));
        proteinCalories = Double.parseDouble(String.format("%.2f", proteinCalories));
        fatCalories = Double.parseDouble(String.format("%.2f", fatCalories));
        sugarsCalories = Double.parseDouble(String.format("%.2f", sugarsCalories));
        totalCalories = Double.parseDouble(String.format("%.2f", totalCalories));



        int carbPercent = (int)((carbCalories / totalCalories) * 100);
        int proteinPercent = (int)((proteinCalories / totalCalories) * 100);
        int fatPercent = (int)((fatCalories / totalCalories) * 100);
        int sugarsPercent = (int)((sugarsCalories / totalCalories) * 100);
        int sodiumPercent = (int)((sodium / 2300) * 100); // 하루 권장량 대비 비율

        int totalPercent = carbPercent + proteinPercent + fatPercent + sugarsPercent; // 현재 합계
        int difference = 100 - totalPercent; // 부족한 퍼센트 계산 (2%)

        carbPercent += difference; // 가장 큰 탄수화물에 부족한 값 추가


        Map<String, Object> result = new HashMap<>();
        result.put("perSevingInt", perSevingInt);
        result.put("perServingUnit", perServingUnit);
        result.put("calories", calories);
        result.put("carb", carbCalories);
        result.put("protein", proteinCalories);
        result.put("fat", fatCalories);
        result.put("sugars", sugarsCalories);
        result.put("sodium", sodium);
        result.put("carbPercent", carbPercent);
        result.put("proteinPercent", proteinPercent);
        result.put("fatPercent", fatPercent);
        result.put("sugarsPercent", sugarsPercent);
        result.put("sodiumPercent", sodiumPercent);

        return result;
    }


    public DiagnosisResult getUserdefaultInfo(int uIdx) {
        return diagnosisResultRepository.getUserdefaultInfo(uIdx);
    }
}

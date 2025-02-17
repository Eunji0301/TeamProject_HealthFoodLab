package com.healthFood.lab.spring2502_heathFood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthFood.lab.spring2502_heathFood.service.FoodDBInfoService;
import com.healthFood.lab.spring2502_heathFood.service.MyMealLogService;
import com.healthFood.lab.spring2502_heathFood.vo.FoodDBInfo;
import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

@Controller
@RequestMapping("/usr/myMealLog")
public class UsrMyMealLogController {

    @Value("${public.api.key}")  // application.yml에서 값 가져오기
    private String apiKey;

    @Autowired
    FoodDBInfoService foodDBInfoService;
    @Autowired
    MyMealLogService myMealLogService;

    FoodDBInfo foodDBInfo;

    @RequestMapping("/main")
    public String  main(){
        return "/usr/myMealLog/myMealLogMain";
    }

    @RequestMapping("/goRecord")
    public String goRecord (String ftMealTime, String ftWriteDate, Model model)throws IOException {
        FoodTracker ft = new FoodTracker();
        ft.setFtMealTime(ftMealTime);
        ft.setFtWriteDate(ftWriteDate);

        String foodTrackerJson = myMealLogService.makeFoodTrackerByJson(ft);
        model.addAttribute("foodTrackerJson", foodTrackerJson);
        return "usr/myMealLog/myMealLogRecord";
    }

    @RequestMapping("/record")
    public String record(){
        return "usr/myMealLog/myMealLogRecord";
    }

    @RequestMapping("/goFoodDictionary")
    public String goFoodDictionary(@RequestParam String searchFoodName,
                                   String foodTrackerJson,
                                   Model model,
                                   @RequestParam(defaultValue = "1") int pageNo) throws IOException {

         int numOfRows = 10;
         String type = "json";

        FoodDBInfo foodDBInfo = new FoodDBInfo();
        foodDBInfo.setPageNo(pageNo);
        foodDBInfo.setNumOfRows(numOfRows);
        foodDBInfo.setType(type);
        foodDBInfo.setFOOD_NM_KR(searchFoodName);

        String foodDBResultJson = foodDBInfoService.makeFoodDBResultByJson(foodDBInfo);

        String foodDBInfoJson = foodDBInfoService.makeFoodDBInfoByJson(foodDBInfo);


        model.addAttribute("foodTrackerJson", foodTrackerJson);
        model.addAttribute("foodDBResultJson", foodDBResultJson);
        model.addAttribute("foodDBInfoJson", foodDBInfoJson);
        return "usr/myMealLog/myMealLogFoodDictionary";
    }

    @RequestMapping("/loadMoreFoodDictionary")
    @ResponseBody
    public String loadMoreFoodDictionary(@RequestBody FoodDBInfo foodDBInfo) throws IOException {

        // 현재 행 개수를 가져와서 10개씩 증가시킴
        int pageNo = foodDBInfo.getPageNo();
        pageNo += 1; // 더보기 버튼 클릭 시 10개씩 추가
        String type = "json";

        System.out.println("Received pageNo: " + pageNo);

        FoodDBInfo foodDBInfoMore = new FoodDBInfo();
        foodDBInfoMore.setPageNo(pageNo);
        foodDBInfoMore.setNumOfRows(foodDBInfo.getNumOfRows());
        foodDBInfoMore.setType(type);
        foodDBInfoMore.setFOOD_NM_KR(foodDBInfo.getFOOD_NM_KR());


        // 서비스 호출하여 새로운 데이터 가져오기
        String sb = foodDBInfoService.makeFoodDBResultByJson(foodDBInfoMore);
        //System.out.println(sb);
        return sb; // JSON 형식으로 반환
    }



    @RequestMapping("/foodDictionary")
    public String foodDictionary (){
        return "usr/myMealLog/myMealLogFoodDictionary";
    }

    @RequestMapping("/goFoodDetail")
    public String goFoodDetail (String foodDBResultJsonOneItem, String foodTrackerJson,  Model model){

       //System.out.println("foodTrackerJson" +foodTrackerJson);
        //System.out.println("foodDBResultJsonOneItem" +foodDBResultJsonOneItem);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> foodDBResultJsonOneItem2;
        Map<String, Object> foodTrackerJson2;
        try {
            foodDBResultJsonOneItem2 = objectMapper.readValue(foodDBResultJsonOneItem, Map.class);
            foodTrackerJson2 = objectMapper.readValue(foodTrackerJson, Map.class);
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
            foodDBResultJsonOneItem2 = Map.of("FOOD_NM_KR", "알 수 없음", "CALORIES", 0); // 기본값 설정
            foodTrackerJson2 = Map.of("FOOD_NM_KR", "알 수 없음", "CALORIES", 0); // 기본값 설정

        }

        model.addAttribute("foodTrackerJson", foodTrackerJson2);
        model.addAttribute("foodDBResultJsonOneItem", foodDBResultJsonOneItem2);
        return "usr/myMealLog/myMealLogFoodDetail";
    }

    @RequestMapping("/foodDetail")
    public String foodDetail(){
        return "usr/myMealLog/myMealLogFoodDetail";
    }


    @PostMapping ("/foodDetailCalculate")
    @ResponseBody
    public Map<String, Object> foodDetailCalculate(@RequestBody Map<String, Object> requestData) {
        Map<String, Object>  foodDetailCalc =  myMealLogService.foodDetailCalculate(requestData);
        return foodDetailCalc;
    }

    @PostMapping ("/saveMealLog")
    public ResponseEntity<String> saveMealLog(HttpSession session,
                                              @RequestParam String ftMealTime,  // 식사 날짜
                                              @RequestParam String ftWriteDate,  // 식사 시점
                                              @RequestParam String ftFoodName,  // 음식명
                                              @RequestParam String ftFoodQuantity,
                                              @RequestParam String ftFoodPortion,
                                              @RequestParam String ftCalorie,
                                              @RequestParam String ftCarb,
                                              @RequestParam String ftProtein,
                                              @RequestParam String ftFat,
                                              @RequestParam String ftSugar,
                                              @RequestParam String ftSodium) {

        String uEmail = (String) session.getAttribute("LoginMemberEmail");

        System.out.println(uEmail);
        System.out.println(ftMealTime);
        System.out.println(ftWriteDate);
        System.out.println(ftFoodName);
        System.out.println(ftFoodQuantity);
        System.out.println(ftFoodPortion);
        System.out.println(ftCalorie);
        System.out.println(ftCarb);
        System.out.println(ftProtein);
        System.out.println(ftFat);
        System.out.println(ftSugar);
        System.out.println(ftSodium);

        return ResponseEntity.ok("저장 완료!");
    }

//
//    //String SearchFoodName, FoodTracker main_record_ft
//    @RequestMapping("/test")
//    @ResponseBody
//    public ResponseEntity<String> saveMealLog(
//            @RequestParam String ftMealTime,  // 식사 날짜
//            @RequestParam String ftWriteDate,  // 식사 시점
//            @RequestParam String ftFoodName,  // 음식명
//            @RequestParam String ftFoodQuantity,
//            @RequestParam String ftFoodPortion,
//            @RequestParam String ftCalorie,
//            @RequestParam String ftCarb,
//            @RequestParam String ftProtein,
//            @RequestParam String ftFat,
//            @RequestParam String ftSugar,
//            @RequestParam String ftSodium,
//            @RequestParam String sodium) {
//
//
//
//        MealLog mealLog = new MealLog(mealDate, mealTime, foodName, selectedValue, perSevingInt, perServingUnit, calories, carb, protein, fat, sugars, sodium);
//        mealLogRepository.save(mealLog);
//
//        return ResponseEntity.ok("저장 완료!");
//    }
//
}

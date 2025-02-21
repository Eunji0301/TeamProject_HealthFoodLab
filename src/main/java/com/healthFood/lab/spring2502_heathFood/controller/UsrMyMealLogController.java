package com.healthFood.lab.spring2502_heathFood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.healthFood.lab.spring2502_heathFood.service.EventDatesService;
import com.healthFood.lab.spring2502_heathFood.service.FoodDBInfoService;
import com.healthFood.lab.spring2502_heathFood.service.MyMealLogService;
import com.healthFood.lab.spring2502_heathFood.service.UserService;
import com.healthFood.lab.spring2502_heathFood.util.Ut;
import com.healthFood.lab.spring2502_heathFood.vo.DiagnosisResult;
import com.healthFood.lab.spring2502_heathFood.vo.FoodDBInfo;
import com.healthFood.lab.spring2502_heathFood.vo.FoodTracker;

import com.healthFood.lab.spring2502_heathFood.vo.ResultData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usr/myMealLog")
public class UsrMyMealLogController {

    @Value("${public.api.key}")  // application.yml에서 값 가져오기
    private String apiKey;

    @Autowired
    public UserService userService;
    @Autowired
    FoodDBInfoService foodDBInfoService;
    @Autowired
    MyMealLogService myMealLogService;


    FoodDBInfo foodDBInfo;

    @RequestMapping("/main")
    public String main(HttpSession session, Model model) {

        //  세션에서 사용자 이메일을 가져와서 uIdx 조회
        String uEmail = (String) session.getAttribute("LoginMemberEmail");
        int uIdx = userService.getUserByuEmailToId(uEmail);

        DiagnosisResult dr =myMealLogService.getUserdefaultInfo(uIdx);



        System.out.println(" myMealLog main"+uIdx);
        System.out.println(" dr main"+dr);

        model.addAttribute("dr", dr);
        return "usr/myMealLog/myMealLogMain";
    }

    @PostMapping("/goRecord")
    public String goRecord(HttpSession session, String ftMealTime, String ftWriteDate, Model model) throws IOException {


        //  세션에서 사용자 이메일을 가져와서 uIdx 조회
        String uEmail = (String) session.getAttribute("LoginMemberEmail");
        int uIdx = userService.getUserByuEmailToId(uEmail);

        FoodTracker ft = new FoodTracker();
        ft.setFtMealTime(ftMealTime);
        ft.setFtWriteDate(ftWriteDate);


        List<FoodTracker> foodTrackerList = myMealLogService.getFoodTrackerById(uIdx, ftWriteDate, ftMealTime);
        int totalCalories = foodTrackerList.stream()
                .filter(food -> food.getFtCalorie() != null)  // null 방지
                .mapToInt(food -> Integer.parseInt(food.getFtCalorie())) // String → int 변환
                .sum();

        // Thymeleaf에 JSON 데이터 전달
        model.addAttribute("totalCalories", totalCalories);
        model.addAttribute("foodTrackerList", foodTrackerList);

        String foodTrackerJson = myMealLogService.makeFoodTrackerByJson(ft);
        model.addAttribute("foodTrackerJson", foodTrackerJson);
        return "usr/myMealLog/myMealLogRecord";
    }

    @PostMapping("/showfoodMealLogRecordList")
    @ResponseBody
    public Map<String, Object> showfoodMealLogRecordList(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> foodDetailCalc = myMealLogService.showfoodMealLogRecordList(requestData);
        return foodDetailCalc;
    }

    @PostMapping("/myMealLogDelect")
    public void myMealLogDelect(@RequestBody String ftIdx) {
        int ftIdxInt = Integer.parseInt(ftIdx.trim());
        System.out.println(ftIdxInt);

    }

    @RequestMapping("/record")
    public String record() {
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

        //System.out.println("Received pageNo: " + pageNo);

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
    public String foodDictionary() {
        return "usr/myMealLog/myMealLogFoodDictionary";
    }

    @RequestMapping("/goFoodDetail")
    public String goFoodDetail(String foodDBResultJsonOneItem, String foodTrackerJson, Model model) {

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

        System.out.println("goFoodDetail foodTrackerJson" + foodTrackerJson);
        model.addAttribute("foodTrackerJson", foodTrackerJson2);
        model.addAttribute("foodDBResultJsonOneItem", foodDBResultJsonOneItem2);
        return "usr/myMealLog/myMealLogFoodDetail";
    }

    @RequestMapping("/foodDetail")
    public String foodDetail() {
        return "usr/myMealLog/myMealLogFoodDetail";
    }


    @PostMapping("/foodDetailCalculate")
    @ResponseBody
    public Map<String, Object> foodDetailCalculate(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> foodDetailCalc = myMealLogService.foodDetailCalculate(requestData);
        return foodDetailCalc;
    }

    @PostMapping("/saveMealLog")
    public String saveMealLog(HttpSession session, String foodTrackerJson,
                              @RequestParam String ftMealTime,
                              @RequestParam String ftWriteDate,
                              @RequestParam String ftFoodName,
                              @RequestParam String ftFoodQuantity,
                              @RequestParam String ftFoodPortion,
                              @RequestParam String ftCalorie,
                              @RequestParam String ftCarb,
                              @RequestParam String ftProtein,
                              @RequestParam String ftFat,
                              @RequestParam String ftSugar,
                              @RequestParam String ftSodium,
                              Model model) throws IOException {

        //  ObjectMapper 객체는 한 번만 선언
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> foodTrackerJson2;

        try {
            foodTrackerJson2 = objectMapper.readValue(foodTrackerJson, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            foodTrackerJson2 = Map.of("FOOD_NM_KR", "알 수 없음", "CALORIES", 0);
        }

        //  세션에서 사용자 이메일을 가져와서 uIdx 조회
        String uEmail = (String) session.getAttribute("LoginMemberEmail");
        int uIdx = userService.getUserByuEmailToId(uEmail);

        //  식사 기록 저장
        myMealLogService.savefoodTracker(ftMealTime, ftWriteDate, ftFoodName, ftFoodQuantity, ftFoodPortion,
                ftCalorie, ftCarb, ftProtein, ftFat, ftSugar, ftSodium, uIdx);
        // 식사 기록 동기화

        // 출력
        System.out.println(ftWriteDate);

        // 저장된 식사 기록 불러오기
        List<FoodTracker> foodTrackerList = myMealLogService.getFoodTrackerById(uIdx, ftWriteDate, ftMealTime);

        // `foodTrackerJson2`를 JSON 문자열로 변환
        String foodTrackerJsonString = objectMapper.writeValueAsString(foodTrackerJson2);

        System.out.println("🔍 saveMealLog foodTrackerJson (JSON String): " + foodTrackerJsonString);

        int totalCalories = foodTrackerList.stream()
                .filter(food -> food.getFtCalorie() != null)  // null 방지
                .mapToInt(food -> Integer.parseInt(food.getFtCalorie())) // String → int 변환
                .sum();

        // Thymeleaf에 JSON 데이터 전달
        model.addAttribute("totalCalories", totalCalories);
        model.addAttribute("foodTrackerList", foodTrackerList);
        model.addAttribute("foodTrackerJson", foodTrackerJsonString);

        return "usr/myMealLog/myMealLogRecord";
    }

}

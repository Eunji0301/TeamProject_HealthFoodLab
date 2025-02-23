package com.ksy.exam.spring2502_heathyfood_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksy.exam.spring2502_heathyfood_test.service.FoodDBInfoService;
import com.ksy.exam.spring2502_heathyfood_test.service.MyMealLogService;
import com.ksy.exam.spring2502_heathyfood_test.vo.FoodDBInfo;
import com.ksy.exam.spring2502_heathyfood_test.vo.FoodTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/myMealLog")
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

    @PostMapping("/foodDetailCalculate")
    public Map<String, Integer> foodDetailCalculate(@RequestBody Map<String, Object> requestData) {
        double selectedValue = Double.parseDouble(requestData.get("value").toString());
        Map<String, Integer> foodDBResultJsonOneItem = (Map<String, Integer>) requestData.get("foodDBResultJsonOneItem");

        System.out.println("selectedValue: "+selectedValue);
        System.out.println("foodDBResultJsonOneItem: "+foodDBResultJsonOneItem);

        String perServing = String.valueOf(foodDBResultJsonOneItem.getOrDefault("SERVING_SIZE", 0));//영양성분함량기준량
        String perServingUnit = "";
        double perSevingInt = 0;
        if(perServing.indexOf("g") > -1){
            perServingUnit = perServing.substring(3);
            perSevingInt = 100;
        }
        if(perServing.indexOf("ml") > -1 ){
            perServingUnit = perServing.substring(3);
            perSevingInt = 100;
        }


        double calories = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM1", 0)));
        double carb = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM7", 0))); //탄수화물
        double protein = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM3", 0))); //단백질
        double fat = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM4", 0))); //지방
        double sugars = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM8", 0))); //당류
        double sodium = Double.parseDouble(String.valueOf(foodDBResultJsonOneItem.getOrDefault("AMT_NUM14", 0))); //나트륨

        System.out.println("perSevingInt: "+perSevingInt);
        System.out.println("Calories: "+calories);
        System.out.println("Carb: "+carb);
        System.out.println("Protein: "+protein);
        System.out.println("Fat: "+fat);
        System.out.println("Sugars: "+sugars);
        System.out.println("Sodium: "+sodium);

        perSevingInt = perSevingInt * selectedValue;
        calories = calories * selectedValue;
        carb = carb * selectedValue;
        protein = protein * selectedValue;
        fat = fat * selectedValue;
        sugars = sugars * selectedValue;
        sodium = sodium * selectedValue;

        double total = calories + carb + protein + fat + sugars + sodium ;
        double carbPercent = (carb/total) * 100 ;
        double proteinPercent = (protein/total) * 100 ;
        double fatPercent = (fat/total) * 100 ;
        double sugarsPercent = (sugars/total) * 100 ;
        double sodiumPercent = (sodium/total) * 100 ;

        System.out.println("perSevingInt: "+perSevingInt);
        System.out.println("perServingUnit: "+perServingUnit);
        System.out.println("Calories2: "+calories);
        System.out.println("Carb2: "+carb);
        System.out.println("Protein2: "+protein);
        System.out.println("Fat2: "+fat);
        System.out.println("Sugars2: "+sugars);
        System.out.println("Sodium2: "+sodium);
        System.out.println("total: "+total);
        System.out.println("carbPercent: "+carbPercent);
        System.out.println("proteinPercent: "+proteinPercent);
        System.out.println("fatPercent: "+fatPercent);
        System.out.println("sugarsPercent: "+sugarsPercent);
        System.out.println("sodiumPercent: "+sodiumPercent);

        return Map.of(
//                "double", number * 2,
//                "square", number * number,
//                "half", number / 2,
//                "increment", number + 10
        );
    }

    //String SearchFoodName, FoodTracker main_record_ft
    @RequestMapping("/test")
    @ResponseBody
    public String test() throws IOException {
        FoodTracker ft = new FoodTracker();
        ft.setFtMealTime("아침식사");
        ft.setFtWriteDate("2024.02.03");

        String foodTrackerJson = myMealLogService.makeFoodTrackerByJson(ft);
        return foodTrackerJson;
    }

}

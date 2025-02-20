package com.healthFood.lab.spring2502_heathFood.controller;


import com.healthFood.lab.spring2502_heathFood.service.CardNewsService;
import com.healthFood.lab.spring2502_heathFood.service.EventDatesService;
import com.healthFood.lab.spring2502_heathFood.vo.CardNewsVo;
import com.healthFood.lab.spring2502_heathFood.vo.EventDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UsrEventDatesController {
    @Autowired
    private EventDatesService eventDatesService;


    // 1️⃣ FullCalendar에서 가져갈 이벤트 날짜 리스트 반환
    @GetMapping("/fullCalendar/events")
    @ResponseBody
    public List<String>  getEvents() {
        return eventDatesService.getEventDates();
    }

    @PostMapping("/fullCalendar/submitDate")
    @ResponseBody
    public String submitDate(@RequestBody DateRequest dateRequest) {
        System.out.println("받은 날짜: " + dateRequest.getSelectedDate());
        return "성공";
    }

    static class DateRequest {
        private String selectedDate;

        public String getSelectedDate() { return selectedDate; }
        public void setSelectedDate(String selectedDate) { this.selectedDate = selectedDate; }
    }
}

package com.healthFood.lab.spring2502_heathFood.service;

import com.healthFood.lab.spring2502_heathFood.repository.AdmRepository;
import com.healthFood.lab.spring2502_heathFood.repository.EventDatesRepository;
import com.healthFood.lab.spring2502_heathFood.vo.EventDates;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventDatesService {

    @Autowired
    EventDatesRepository eventDatesRepository;

    // 생성자
    public EventDatesService(EventDatesRepository eventDatesRepository) {
        this.eventDatesRepository = eventDatesRepository;
    }

    // 중복 없는 날짜 리스트 반환
    public List<String> getEventDates() {
        return eventDatesRepository.findAllEventDates();
    }

    // FoodTracker → EventDates 동기화 실행
    public void syncEventDates() {
        eventDatesRepository.syncEventDates();
    }

    // 오늘 날짜가 이벤트에 포함되는지 확인
    public boolean isTodayInEvents() {
        String today = LocalDate.now().toString();
        List<String> eventDates = getEventDates();
        return eventDates.contains(today);
    }

}

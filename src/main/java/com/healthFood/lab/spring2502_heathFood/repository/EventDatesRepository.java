package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.EventDates;
import com.healthFood.lab.spring2502_heathFood.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EventDatesRepository {

    // 중복 없는 이벤트 날짜 리스트 가져오기
    @Select("SELECT edEventDate FROM EVENT_DATES")
    List<String> findAllEventDates();

    // FoodTracker의 데이터를 EventDates에 동기화
    @Insert("INSERT IGNORE INTO EVENT_DATES (edEventDate, uIdx) SELECT DISTINCT ftWriteDate, uIdx FROM FOOD_TRACKER ")
    void syncEventDates();
}



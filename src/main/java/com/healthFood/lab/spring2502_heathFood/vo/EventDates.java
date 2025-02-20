package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDates {
    private int edIdx;
    private String edEventDate;
    private String edUpdateDate;
    private String edCreateAt;
    private int uIdx;


}

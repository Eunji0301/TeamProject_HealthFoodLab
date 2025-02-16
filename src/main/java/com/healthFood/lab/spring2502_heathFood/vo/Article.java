package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private String title;
    private String body;
}

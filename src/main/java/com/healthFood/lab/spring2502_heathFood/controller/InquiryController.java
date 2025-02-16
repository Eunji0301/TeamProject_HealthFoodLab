package com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InquiryController {
    @RequestMapping("/usr/inquiry/inquiryList")
    public String showInquiryList() {
        return "usr/inquiry/inquiryList";
    }

    @RequestMapping("/usr/inquiry/inquiryModify")
    public String showInquiryModify() {
        return "usr/inquiry/inquiryModify";
    }

    @RequestMapping("/usr/inquiry/inquiryWrite")
    public String showInquiryWrite() {
        return "usr/inquiry/inquiryWrite";
    }

    @RequestMapping("/usr/inquiry/inquiryDetail")
    public String showInquiryDetail() {
        return "usr/inquiry/inquiryDetail";
    }
}

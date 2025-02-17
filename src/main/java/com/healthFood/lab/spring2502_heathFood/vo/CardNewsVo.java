package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardNewsVo {
    private int cIdx;
    private String cnTitle;
    private String cnFilename;
    private String cnWriter;
    private String cnContact;
    private String cnImage;
    private MultipartFile uploadFile;
    private LocalDateTime cnWriteDay;
    private String cnDelyn ;
    private LocalDateTime cnDelDate ;
    private LocalDateTime cnModifyDate ;
    private LocalDateTime cnCreateAt;
}

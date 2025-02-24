package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyChallengeVo {
    private int uccIdx;
    private int uIdx; // ✅ 유저 ID 추가
    private String uccTitle;
    private String uccContents;
    private String uccImage;
    private MultipartFile uploadFile;
    private String uccFilename;
    private LocalDateTime uccWriteDay;
    private String uccDelyn;
    private LocalDateTime uccDelDate;
    private LocalDateTime uccModifyDate;
    private LocalDateTime uccCreateAt;
}
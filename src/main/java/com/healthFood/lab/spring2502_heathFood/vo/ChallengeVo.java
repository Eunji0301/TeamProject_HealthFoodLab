package com.healthFood.lab.spring2502_heathFood.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeVo {
    private int ciIdx;
    private String ciTitle;
    private String ciContents;
    private String ciImage;
    private MultipartFile uploadFile;
    private String ciFilename;
    private String ciDuration;
    private LocalDateTime ciWriteDay;
    private String ciDelyn;
    private LocalDateTime ciDelDate;
    private LocalDateTime ciModifyDate;
    private LocalDateTime ciCreateAt;
}

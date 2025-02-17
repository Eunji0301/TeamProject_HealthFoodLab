package com.healthFood.lab.spring2502_heathFood.service;

import com.healthFood.lab.spring2502_heathFood.repository.CardNewsRepository;
import com.healthFood.lab.spring2502_heathFood.vo.CardNewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CardNewsService {
    @Autowired
    private CardNewsRepository cardNewsRepository;

    // 생성자
    public CardNewsService(CardNewsRepository cardNewsRepository) {
        this.cardNewsRepository = cardNewsRepository;
    }

    // 카드 뉴스 전체 조회
    public List<CardNewsVo> getAllCardNews() {
        return cardNewsRepository.getAllCardNews();
    }

    // 특정 카드 뉴스 조회
    public CardNewsVo getCardNewsById(int cIdx) {
        return cardNewsRepository.getCardNewsById(cIdx);
    }

    // 카드 뉴스 등록
    public void insertCardNews(CardNewsVo cardNews) {
        cardNewsRepository.insertCardNews(cardNews);
    }

    // 카드 뉴스 수정
    public void updateCardNews(CardNewsVo cardNews) {
        cardNewsRepository.updateCardNews(cardNews);
    }

    // 카드 뉴스 삭제
    public void deleteCardNews(int cIdx) {
        cardNewsRepository.deleteCardNews(cIdx);
    }

    private final String uploadDir = "C:\\UploadImage\\";

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName; // DB에는 파일 이름(경로)을 저장
    }


}

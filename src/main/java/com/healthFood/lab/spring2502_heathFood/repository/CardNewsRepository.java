package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.CardNewsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardNewsRepository {
    // 전체 조회
    @Select("SELECT * FROM CARDNEWS WHERE cnDelyn = 'N' ORDER BY cnWriteDay DESC")
    List<CardNewsVo> getAllCardNews();

    // 단일 조회
    @Select("SELECT * FROM CARDNEWS WHERE cIdx = #{cIdx} AND cnDelyn = 'N'")
    CardNewsVo getCardNewsById(int cIdx);

    // 등록
    @Insert("""
        INSERT INTO CARDNEWS (cnTitle, cnFilename, cnWriter, cnContact, cnImage, cnWriteDay, cnModifyDate, cnCreateAt)
        VALUES (#{cnTitle}, #{cnFilename}, #{cnWriter}, #{cnContact}, #{cnImage}, NOW(), NOW(), NOW())
    """)
    @Options(useGeneratedKeys = true, keyProperty = "cIdx")
    void insertCardNews(CardNewsVo cardNews);

    // 수정
    @Update("""
        UPDATE CARDNEWS
        SET cnTitle = #{cnTitle}, cnFilename = #{cnFilename}, cnWriter = #{cnWriter},
            cnContact = #{cnContact}, cnImage = #{cnImage}, cnModifyDate = NOW()
        WHERE cIdx = #{cIdx}
    """)
    void updateCardNews(CardNewsVo cardNews);

    // 삭제 (실제 삭제가 아닌 삭제 표시)
    @Update("UPDATE CARDNEWS SET cnDelyn = 'Y', cnDelDate = NOW() WHERE cIdx = #{cIdx}")
    void deleteCardNews(int cIdx);
}

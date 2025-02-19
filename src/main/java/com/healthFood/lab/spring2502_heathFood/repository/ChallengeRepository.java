package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.ChallengeVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChallengeRepository {

    // 전체 조회
    @Select("SELECT * FROM CHALLENGE_INFO WHERE ciDelyn = 'N' ORDER BY ciWriteDay DESC")
    List<ChallengeVo> getAllChallenges();

    // 단일 조회
    @Select("SELECT * FROM CHALLENGE_INFO WHERE ciIdx = #{ciIdx} AND ciDelyn = 'N'")
    ChallengeVo getChallengeById(int ciIdx);

    // 등록
    @Insert("""
        INSERT INTO CHALLENGE_INFO (ciTitle, ciContents, ciImage, ciFilename, ciDuration, ciWriteDay, ciModifyDate, ciCreateAt)
        VALUES (#{ciTitle}, #{ciContents}, #{ciImage}, #{ciFilename}, #{ciDuration}, NOW(), NOW(), NOW())
    """)
    @Options(useGeneratedKeys = true, keyProperty = "ciIdx")
    void insertChallenge(ChallengeVo challenge);

    // 수정
    @Update("""
        UPDATE CHALLENGE_INFO
        SET ciTitle = #{ciTitle}, ciContents = #{ciContents}, ciImage = #{ciImage}, ciFilename = #{ciFilename},
            ciDuration = #{ciDuration}, ciModifyDate = NOW()
        WHERE ciIdx = #{ciIdx}
    """)
    void updateChallenge(ChallengeVo challenge);

    // 삭제 (실제 삭제가 아닌 삭제 표시)
    @Update("UPDATE CHALLENGE_INFO SET ciDelyn = 'Y', ciDelDate = NOW() WHERE ciIdx = #{ciIdx}")
    void deleteChallenge(int ciIdx);
}


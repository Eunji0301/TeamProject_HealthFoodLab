package com.healthFood.lab.spring2502_heathFood.repository;

import com.healthFood.lab.spring2502_heathFood.vo.MyChallengeVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyChallengeRepository {

    // 전체 조회
    @Select("SELECT * FROM USER_CHALLENGE_CERTIFICATION WHERE uccDelyn = 'N' ORDER BY uccWriteDay DESC")
    List<MyChallengeVo> getAllMyChallenges();

    // 단일 조회
    @Select("SELECT * FROM USER_CHALLENGE_CERTIFICATION WHERE uccIdx = #{uccIdx} AND uccDelyn = 'N'")
    MyChallengeVo getMyChallengeById(int uccIdx);

    // 등록
    @Insert("""
        INSERT INTO USER_CHALLENGE_CERTIFICATION (uccTitle, uccContents, uccImage, uccFilename, uccWriteDay, uccModifyDate, uccCreateAt)
        VALUES (#{uccTitle}, #{uccContents}, #{uccImage}, #{uccFilename}, NOW(), NOW(), NOW())
    """)
    @Options(useGeneratedKeys = true, keyProperty = "uccIdx")
    void insertMyChallenge(MyChallengeVo myChallenge);

    // 수정
    @Update("""
        UPDATE USER_CHALLENGE_CERTIFICATION
        SET uccTitle = #{uccTitle}, 
            uccContents = #{uccContents},
            uccModifyDate = NOW(),
            uccImage = CASE 
                WHEN #{uccImage} IS NOT NULL AND #{uccImage} != '' THEN #{uccImage} 
                ELSE uccImage 
            END,
            uccFilename = CASE 
                WHEN #{uccFilename} IS NOT NULL AND #{uccFilename} != '' THEN #{uccFilename} 
                ELSE uccFilename 
            END
        WHERE uccIdx = #{uccIdx}
    """)
    void updateMyChallenge(MyChallengeVo myChallenge);



//    // 삭제 (실제 삭제가 아닌 삭제 표시)
//    @Update("UPDATE USER_CHALLENGE_CERTIFICATION SET uccDelyn = 'Y', uccDelDate = NOW() WHERE uccIdx = #{uccIdx}")
//    void deleteMyChallenge(int uccIdx);
}

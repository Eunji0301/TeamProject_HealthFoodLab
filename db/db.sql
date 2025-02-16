# DB 세팅
DROP DATABASE IF EXISTS `spring2502_heathyfood_test`;
CREATE DATABASE `spring2502_heathyfood_test`;
USE `spring2502_heathyfood_test`;

# 유저 테이블 생성
DROP TABLE IF EXISTS USER;
CREATE TABLE USER
(
    uIdx INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 유저 인덱스
    uEmail VARCHAR(255) NOT NULL, -- 유저 이메일
    uPwd VARCHAR(50) NOT NULL, -- 유저 비밀번호
    uName VARCHAR(50) NOT NULL, -- 유저 이름
    uGender CHAR(1) NOT NULL, -- 유저 성별
    uBirth VARCHAR(10) NOT NULL, -- 유저 생년월일
    uPhone VARCHAR(20) NOT NULL, -- 유저 연락처
    uNickName VARCHAR(20) NOT NULL, -- 유저 닉네임
    uThumbnail TEXT, -- 유저 썸네일 (썸네일 기본 유지 > titlelogo, 유저 썸네일 수정 > 수정된 썸네일 이미지)
    uIp VARCHAR(50) NOT NULL, -- 유저 아이피
    uEnterDate DATETIME DEFAULT NOW() NOT NULL, -- 유저 가입날짜
    uUpdateDate DATETIME, -- 유저 수정날짜
    uDelyn CHAR(1) DEFAULT 'N' NOT NULL, -- 유저 탈퇴여부 (DEFAULT 'N')
    uDelDate DATETIME, -- 유저 탈퇴날짜
    uTOSAgree CHAR(1) DEFAULT 'N' NOT NULL, -- 유저 이용약관 동의
    uPIPAgree CHAR(1) DEFAULT 'N' NOT NULL, -- 유저 개인정보 동의
    uAdminPwd VARCHAR(50) , -- 관리자용 비밀번호
    uRole ENUM('ADMIN','USER') NOT NULL, -- 유저 분류 (ENUM('ADMIN','USER'))
    uLevel INT DEFAULT 1 , -- 유저 현재 레밸
    uExp INT DEFAULT 0 , -- 유저 누적 경험치
    uMileage INT DEFAULT 0 , -- 유저 누적 경험치
    uCreateAt DATETIME DEFAULT NOW() NOT NULL,
    uResetToken TEXT-- 해당 정보 생성 날짜
);
SELECT * FROM  USER;
SELECT * FROM USER WHERE uResetToken="50552bf0-a0f8-46d2-8626-cfc08f655f64";
################################################
# User 테스트 데이터
INSERT INTO USER(uEmail, uPwd, uName, uGender, uBirth, uPhone, uNickName, uThumbnail, uIp, uTOSAgree, uPIPAgree, uAdminPwd, uRole)
VALUES("hellohealthy1125@gmail.com", "0000", "관리자0", "M", "20021010", "01012341234", "관리자0", "themnailBasic.png", "127.0.0.1","Y","Y", "hh1125!!", "ADMIN");
INSERT INTO USER(uEmail, uPwd, uName, uGender, uBirth, uPhone, uNickName, uThumbnail, uIp, uTOSAgree, uPIPAgree,  uAdminPwd, uRole)
VALUES("forstudy.eunji@gmail.com", "0000", "관리자1", "M", "20021010", "01012341234", "관리자1", "themnailBasic.png", "127.0.0.1","Y","Y", "hh1125!!", "ADMIN");
INSERT INTO USER(uEmail, uPwd, uName, uGender, uBirth, uPhone, uNickName, uThumbnail, uIp, uTOSAgree, uPIPAgree,  uAdminPwd, uRole)
VALUES("whiteclark123@gmail.com", "0000", "관리자2", "M", "20021010", "01012341234", "관리자2", "themnailBasic.png", "127.0.0.1","Y","Y", "hh1125!!", "ADMIN");
INSERT INTO USER(uEmail, uPwd, uName, uGender, uBirth, uPhone, uNickName, uThumbnail, uIp, uTOSAgree, uPIPAgree,  uAdminPwd, uRole)
VALUES("it5441233@gmail.com", "0000", "관리자3", "M", "20021010", "01012341234", "관리자3", "themnailBasic.png", "127.0.0.1","Y","Y", "hh1125!!", "ADMIN");
INSERT INTO USER(uEmail, uPwd, uName, uGender, uBirth, uPhone, uNickName, uThumbnail, uIp, uTOSAgree, uPIPAgree,  uAdminPwd, uRole)
VALUES("kakasska1212@gmail.com", "0000", "관리자4", "M", "20021010", "01012341234", "관리자4", "themnailBasic.png", "127.0.0.1","Y","Y", "hh1125!!", "ADMIN");

################################################

# FOOD_TRACKER 테이블 생성
DROP TABLE IF EXISTS FOOD_TRACKER;
CREATE TABLE FOOD_TRACKER
(
    `ftIdx`           INT                          NOT NULL    AUTO_INCREMENT COMMENT '식사 기록 인덱스. 식사 기록 인덱스',
    `ftWriteDate`     DATETIME                     NULL        COMMENT '식사 기록 날짜. 식사 기록 날짜',
    `ftMealTime`      ENUM('아침','점심','저녁','간식')    NULL        COMMENT '식사 시간대 (아침/점심/저녁/간식). 식사 시간대 (아침/점심/저녁/간식)',
    `ftFoodName`      VARCHAR(100)                 NULL        COMMENT '식사 음식 이름. 식사 음식 이름',
    `ftFoodQuantity`  VARCHAR(20)                  NULL        COMMENT '식사 수량. 식사 수량',
    `ftFoodPortion`   VARCHAR(20)                  NULL        COMMENT '식사 분량. 식사 분량',
    `ftCalorie`       VARCHAR(20)                  NULL        COMMENT '식사 열량 (kcal). 식사 열량 (kcal)',
    `ftCarb`          VARCHAR(20)                  NULL        COMMENT '식사 탄수화물 (g). 식사 탄수화물 (g)',
    `ftProtein`       VARCHAR(20)                  NULL        COMMENT '식사 단백질 (g). 식사 단백질 (g)',
    `ftFat`           VARCHAR(20)                  NULL        COMMENT '식사 지방 (g). 식사 지방 (g)',
    `ftSugar`         VARCHAR(20)                  NULL        COMMENT '식사 당 (g). 식사 당 (g)',
    `ftSodium`        VARCHAR(20)                  NULL        COMMENT '식사 나트륨 (mg). 식사 나트륨 (mg)',
    `ftCreateAt`      DATETIME                     NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '해당 정보 생성 날짜. 해당 정보 생성 날짜',
    PRIMARY KEY (ftIdx)
);

-- 테이블 Comment 설정 SQL - FOOD_TRACKER
ALTER TABLE FOOD_TRACKER COMMENT 'FOOD_TRACKER. 매 끼니 마다 유저가 먹은 음식 내용들을 기록하는 테이블';

-- Foreign Key 설정 SQL - FOOD_TRACKER(ftIdx) -> USER(uIdx)
ALTER TABLE FOOD_TRACKER
    ADD CONSTRAINT FK_FOOD_TRACKER_ftIdx_USER_uIdx FOREIGN KEY (ftIdx)
        REFERENCES USER (uIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - FOOD_TRACKER(ftIdx)
-- ALTER TABLE FOOD_TRACKER
-- DROP FOREIGN KEY FK_FOOD_TRACKER_ftIdx_USER_uIdx;
SELECT * FROM  FOOD_TRACKER;

######################################################
DROP TABLE IF EXISTS NUTRITION_STANDARD;
CREATE TABLE NUTRITION_STANDARD
(
    `nsIdx`          INT            NOT NULL    AUTO_INCREMENT COMMENT '적정 섭취 기준 ID. 적정 섭취 기준 ID',
    `nsCalorie`      VARCHAR(20)    NULL        COMMENT '적정 섭취 열량 (kcal). 적정 섭취 열량 (kcal)',
    `nsCarb`         VARCHAR(20)    NULL        COMMENT '적정 섭취 탄수화물 (g). 적정 섭취 탄수화물 (g)',
    `nsProtein`      VARCHAR(20)    NULL        COMMENT '적정 섭취 단백질 (g). 적정 섭취 단백질 (g)',
    `nsFat`          VARCHAR(20)    NULL        COMMENT '적정 섭취 지방 (g). 적정 섭취 지방 (g)',
    `nsSugar`        VARCHAR(20)    NULL        COMMENT '적정 섭취 당 (g). 적정 섭취 당 (g)',
    `nsFiber`        VARCHAR(20)    NULL        COMMENT '적정 섭취 식이섬유 (g). 적정 섭취 식이섬유 (g)',
    `nsCholesterol`  VARCHAR(20)    NULL        COMMENT '적정 섭취 콜레스테롤 (mg). 적정 섭취 콜레스테롤 (mg)',
    `nsSodium`       VARCHAR(20)    NULL        COMMENT '적정 섭취 나트륨 (mg). 적정 섭취 나트륨 (mg)',
    `nsCreateAt`     DATETIME       NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '해당 정보 생성 날짜. 해당 정보 생성 날짜',
    PRIMARY KEY (nsIdx)
);

-- 테이블 Comment 설정 SQL - NUTRITION_STANDARD
ALTER TABLE NUTRITION_STANDARD COMMENT 'NUTRITION_STANDARD. 예제음식과 식단음식의 영양소 데이터를 받아서 현재 회원의 건강상태에 비해 영양 기준이 적절한지 판단하는 기준치 테이블';

-- Foreign Key 설정 SQL - NUTRITION_STANDARD(nsIdx) -> USER(uIdx)
ALTER TABLE NUTRITION_STANDARD
    ADD CONSTRAINT FK_NUTRITION_STANDARD_nsIdx_USER_uIdx FOREIGN KEY (nsIdx)
        REFERENCES USER (uIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - NUTRITION_STANDARD(nsIdx)
-- ALTER TABLE NUTRITION_STANDARD
-- DROP FOREIGN KEY FK_NUTRITION_STANDARD_nsIdx_USER_uIdx;
######################################################
DROP TABLE IF EXISTS DAILY_SUMMARY;
-- 테이블 생성 SQL - DAILY_SUMMARY
CREATE TABLE DAILY_SUMMARY
(
    `dsIdx`           INT                 NOT NULL    AUTO_INCREMENT COMMENT '일별 요약 인덱스. 일별 요약 인덱스',
    `dsTotalCalorie`  VARCHAR(20)         NULL        COMMENT '총 섭취 열량 (kcal). 총 섭취 열량 (kcal)',
    `dsTotalCarb`     VARCHAR(20)         NULL        COMMENT '총 섭취 탄수화물 (g). 총 섭취 탄수화물 (g)',
    `dsTotalProtein`  VARCHAR(20)         NULL        COMMENT '총 섭취 단백질 (g). 총 섭취 단백질 (g)',
    `dsTotalFat`      VARCHAR(20)         NULL        COMMENT '총 섭취 지방 (g). 총 섭취 지방 (g)',
    `dsTotalSugar`    VARCHAR(20)         NULL        COMMENT '총 섭취 당 (g). 총 섭취 당 (g)',
    `dsTotalSodium`   VARCHAR(20)         NULL        COMMENT '총 섭취 나트륨 (mg). 총 섭취 나트륨 (mg)',
    `dsStatus`        ENUM('주의', '양호')    NULL        COMMENT '총 현재 상태. 총 현재 상태',
    `dsCreatedAt`     DATETIME            NULL        DEFAULT CURRENT_TIMESTAMP COMMENT '총 섭취 생성된 날짜. 총 섭취 생성된 날짜',
    PRIMARY KEY (dsIdx)
);

-- 테이블 Comment 설정 SQL - DAILY_SUMMARY
ALTER TABLE DAILY_SUMMARY COMMENT 'DAILY_SUMMARY. 유저 식단 일일 통계 테이블';

-- Foreign Key 설정 SQL - DAILY_SUMMARY(dsIdx) -> NUTRITION_STANDARD(nsIdx)
ALTER TABLE DAILY_SUMMARY
    ADD CONSTRAINT FK_DAILY_SUMMARY_dsIdx_NUTRITION_STANDARD_nsIdx FOREIGN KEY (dsIdx)
        REFERENCES NUTRITION_STANDARD (nsIdx) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - DAILY_SUMMARY(dsIdx)
-- ALTER TABLE DAILY_SUMMARY
-- DROP FOREIGN KEY FK_DAILY_SUMMARY_dsIdx_NUTRITION_STANDARD_nsIdx;

CREATE TABLE CARDNEWS (
                          cIdx INT AUTO_INCREMENT PRIMARY KEY,
                          cnTitle TEXT NOT NULL,
                          cnFilename VARCHAR(255) NOT NULL,
                          cnWriter VARCHAR(50) NOT NULL,  -- 작성자 이름 (그냥 저장)
                          cnContact VARCHAR(20) NOT NULL,
                          cnImage VARCHAR(255) NOT NULL,
                          cnWriteDay DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          cnDelyn CHAR(1) NOT NULL DEFAULT 'N',
                          cnDelDate DATETIME NULL,
                          cnModifyDate DATETIME NOT NULL,
                          cnCreateAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO CARDNEWS (cnTitle, cnFilename, cnWriter, cnContact, cnImage, cnWriteDay, cnDelyn, cnDelDate, cnModifyDate, cnCreateAt)
VALUES
    ('AI 혁신 시대, 우리의 미래는?', 'ai_future.jpeg', '김철수', '010-1234-5678', '/images/ai_future.jpeg', NOW(), 'N', NULL, NOW(), NOW()),
    ('자율주행 자동차, 어디까지 왔나?', 'self_drive.jpeg', '이영희', '010-2345-6789', '/images/self_drive.jpeg', NOW(), 'N', NULL, NOW(), NOW()),
    ('스마트폰 중독, 해법은?', 'smartphone_addiction.jpeg', '박민준', '010-3456-7890', '/images/smartphone_addiction.jpeg', NOW(), 'N', NULL, NOW(), NOW()),
    ('메타버스, 새로운 기회의 장', 'metaverse.jpeg', '정소영', '010-4567-8901', '/images/metaverse.jpeg', NOW(), 'N', NULL, NOW(), NOW()),
    ('로봇이 인간을 대체할까?', 'robot_human.jpeg', '최수민', '010-5678-9012', '/images/robot_human.jpeg', NOW(), 'N', NULL, NOW(), NOW()),
    ('친환경 에너지가 대세!', 'eco_energy.jpeg', '한지우', '010-6789-0123', '/images/eco_energy.jpeg', NOW(), 'N', NULL, NOW(), NOW());

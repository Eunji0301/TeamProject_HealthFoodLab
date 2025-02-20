### 🥗 HealthFoodLab : 개인 건강 데이터 및 식습관 기반 맞춤형 건강 정보 서비스 제공 플랫폼
<hr>

![](https://velog.velcdn.com/images/um0114/post/a1374740-fd5c-4bd5-82d1-3323e32a5f6f/image.png)

## 📌 프로젝트 소개
<hr>

- HealthFoodLab은 데이터 기반 영양 분석과 개인화된 솔루션을 통해 질병 예방과 건강 증진을 목표로 하며 과학적 근거를 바탕으로 개인별 맞춤형 식단과 지속 가능한 식습관을 제안합니다.
- 유저는 개인 맞춤형 식단, 영양 분석, 질병 예방 정보 등을 제공받아 건강한 삶을 위한 최적의 솔루션을 얻을 수 있습니다.

## 📅 개발 기간

<hr>

- 2025.01.14 ~ 2025.02.25

## 👨‍👩‍👧‍👦 팀원 구성
<hr>

| 엄은지 | 김건주 | 김세옥 | 김시연 |
| :-: | :-: | :-: | :-: |
| ![](https://velog.velcdn.com/images/um0114/post/939f2ad4-e6e6-4cf0-9958-d4167c6115db/image.webp)| ![](https://velog.velcdn.com/images/um0114/post/f9248b4b-f14f-47f2-ba7e-426e203da206/image.jpg)| ![](https://velog.velcdn.com/images/um0114/post/913e1879-f626-4c17-96bd-3193d938c13f/image.webp)| ![](https://velog.velcdn.com/images/um0114/post/d817fcc2-5b32-4997-af43-752add488a24/image.png)|
| forstudy.eunji@gmail.com | whiteclark123@gmail.com  | it5441233@gmail.com | kakasska1212@gmail.com |

## 🛠 기술스택
<hr>

- 언어
    - Java
- Front-end
    - HTML, CSS, JavaScript, jQuery, Tailwind
    - API : Google OAuth, Kakao OAuth, Full Calendar, 공공데이터(식품의약품안전처_식품영양성분DB정보)
- Back-end
    - 프레임워크 : SpringBoot
    - 라이브러리 : Lombok, Tomcat
    - 템플릿 엔진 : Thymeleaf
    - ORM : MyBatis
- DB
    - MySQL
    - 쿼리 브라우저 : SQLyog
- 버전 관리
    - Git, GitHub
- 디자인
    - Figma
- 협업 툴
    - Discord, Google 스프레드시트, Google 문서, 클로바노트
- 개발 환경
    - JDK, MAVEN, Spring Tool Suit 4, Intellij, Window 10
- 웹-프레임워크: 구글 앱 스크립트

## ERD 구조
<hr>


- ERD 링크

## 🙋‍♂️ 역할 분담
<hr>

### 🐱‍👓엄은지
◆ 피그마 디자인
- 유저 마이페이지
- 카드뉴스 리스트
- 카드뉴스 상세보기
- 관리자 카드뉴스 작성/수정/리스트
- 관리자 챌린지 작성/수정/리스트
- 관리자 페이지
- 챌린지 리스트/상세보기
- MY챌린지 인증글 작성/수정/리스트/상세보기,
- 완료/미완료/진행중 챌린지
- 최다성공/최고인기 챌린지
- MY냉장고 메인/재료추가

◆ 프론트엔드
- 챌린지 페이지 : 작성/수정 폼, 리스트, 상세보기
- 카드뉴스 페이지 : 작성/수정 폼, 리스트, 상세보기

◆ 백엔드
- 관리자 : 카드뉴스, 챌린지 CRUD

◆ ERD
- 카드뉴스
- 챌린지

### 🐱‍💻김건주
◆ 프론트엔드
- 영양진단 페이지 : 영양진단 결과

◆ 백엔드
- 신체정보 및 식단진단 정보 입력 : 적정수치량과 비교하여 임의 계산식으로 현재 건강에 따른 점수판 구현
- MY냉장고 : 재료 추가 및 삭제

◆ ERD
- 유저 영양진단
- 유저 식사진단
- 100g 기준 적정 식사 정보
- 각 유저 별 평가 점수
- 유저 냉장고

### 🐱‍🚀 김세옥
◆ 프론트엔드
- MY챌린지 페이지 : 인증글 작성/수정/리스트/상세보기
- MY페이지
- 1:1문의 페이지 : 문의 글 작성/수정/리스트

◆ 백엔드
- MY챌린 CR

◆ ERD
- 유저 챌린지
- 유저 챌린지 인증

### 🐱‍🐉 김시연
◆ 디자인
- 1:1문의 등록/수정/리스트
- 회원가입
- 메인페이지(로그인)
- 아이디/비밀번호 찾기/결과, 비밀번호 변경
- MY식사일지, 한 끼 식사기록, 음식 검색, 음식 영양소
- 진단 폼(기본정보, 식습관), 진단 결과

◆ 프론트엔드
- 회원관리 페이지 : 로그인, 회원가입 등 유저 관련
- MY식사일지 페이지 : 끼니별 식사 기록, 음식 검색, 음식 영양소
- 영양진단 페이지 : 진단 동의, 진단 폼(기본정보, 식습관)

◆ 백엔드
- 회원관리
- MY식사일지

◆ ERD
- 회원
- 달력 표시
- 유저가 섭취한 식사 기록

## 💡 페이지별 기능
### [유저]

#### [메인화면(로그인)]
![](https://velog.velcdn.com/images/um0114/post/24bd4f3a-ed9c-409c-87b2-f84fd8bf507e/image.png)
- 처음 시작 화면은 시작 로그인 화면과 함께 카드뉴스 및 챌린지가 표시되는 페이지입니다.
- 메인 페이지는 사용자에게 직관적인 네비게이션을 제공하며 각 기능 섹션이 구분되어 있습니다.
- 각 섹션은 유저의 관심사에 맞게 개인화된 콘텐츠를 보여주고 직관적인 디자인을 통해 편리한 접근성을 제공합니다.
- 이메일과 비밀번호로 로그인을 합니다.

#### [회원가입]
![](https://velog.velcdn.com/images/um0114/post/5c14eb68-4420-4f22-a7c7-3c8f3160c445/image.png)
- 기본 정보 입력 후 회원가입을 합니다.

#### [비밀번호 변경]
![](https://velog.velcdn.com/images/um0114/post/9b2accee-cd0a-4b2c-9407-14d1b45f8065/image.png)
- 보안 강화를 위해 기존의 비밀번호를 안전하게 변경할 수 있습니다.

#### [이메일 찾기 및 결과]
![](https://velog.velcdn.com/images/um0114/post/94bb4b3a-8d59-4af5-b1e0-d2773781b6da/image.png)
- 등록된 이메일 주소를 통해 계정을 찾을 수 있습니다.
  ![](https://velog.velcdn.com/images/um0114/post/da08b0dc-3ec2-499e-b785-08ffd2f3481d/image.png)
- 입력한 정보에 해당하는 계정이 존재하면 시스템은 해당 계정에 등록된 이메일 주소를 사용자에게 제공합니다.
- 계정 복구 또는 로그인에 도움이 됩니다.

#### [비밀번호 찾기 및 결과]
![](https://velog.velcdn.com/images/um0114/post/1ac7fcdc-dc52-415d-a40f-7f1297dee61e/image.png)
- 계정에 등록된 이름과 이메일 주소를 입력합니다.
  ![](https://velog.velcdn.com/images/um0114/post/ca686aec-7d80-44a7-8ed9-1c9a7bf961b1/image.png)
- 입력한 정보가 시스템에 등록된 계정과 일치할 경우 새 비밀번호를 설정할 수 있습니다.

#### [진단 동의]
![](https://velog.velcdn.com/images/um0114/post/b8714c4d-8ae4-452a-b59c-c42152ffac42/image.png)
- 영양진단을 시작하려면 개인정보 수집 및 이용에 동의해야 합니다.

#### [진단 폼]
![](https://velog.velcdn.com/images/um0114/post/30fac8ac-c38a-4715-9bcc-ab2020cb2098/image.png)
- 신체 정보, 질환 여부, 활동량 및 음주량을 입력합니다.

#### [식사문진표]
![](https://velog.velcdn.com/images/um0114/post/399a098b-0e28-4fe5-ac8b-7e58e7444ab6/image.png)
![](https://velog.velcdn.com/images/um0114/post/b65fe2b4-bac2-47eb-b81a-857fcfd05654/image.png)
![](https://velog.velcdn.com/images/um0114/post/05f2457b-2132-4600-90ad-d81d54fc6282/image.png)
- 각 카테고리별 음식의 주간 섭취 횟수를 기록합니다.

#### [영양진단 결과]
![](https://velog.velcdn.com/images/um0114/post/4c9bb5e8-a110-4610-b559-df1b14429cbc/image.png)
- 적정 섭취량과 비교한 실제 섭취량 점수, 각 영양소별 부족/적정/과다 수준, 그리고 현재 식습관으로 인한 위험 질환을 제공합니다.

#### [MY식사일지]
![](https://velog.velcdn.com/images/um0114/post/8af787a0-c421-4f8c-abdd-d7c750d4da3f/image.png)
- 하루 섭취한 음식의 총 영양소 정보와 끼니별 식사 리스트가 저장됩니다.

#### [끼니별 식사 기록]
![](https://velog.velcdn.com/images/um0114/post/93022670-73c1-48c0-a0cb-f68985cd485f/image.png)
- 끼니별로 섭취한 음식을 검색하여 선택 후 저장할 수 있습니다.

#### [음식 영양소 사전]
![](https://velog.velcdn.com/images/um0114/post/0a7755cf-cb0c-4c30-8430-960084f0a40d/image.png)
- 음식명, 수량, 중량(g), 칼로리 등의 정보가 제공됩니다.

#### [음식 상세]
![](https://velog.velcdn.com/images/um0114/post/73aee839-5fdb-4df4-85ba-1560c3ed720e/image.png)
- 각 음식에 대한 영양소(단백질, 지방, 탄수화물 등) 정보가 제공됩니다.

#### [MY냉장고 식재료 관리]
![](https://velog.velcdn.com/images/um0114/post/ce7c6289-5bf1-47ef-8a85-a84286bcd2a8/image.png)
- 냉장고 및 냉동고에 보관 중인 식품의 종류를 확인할 수 있습니다.

#### [MY냉장고 재료추가]
![](https://velog.velcdn.com/images/um0114/post/e9b847f2-7935-4ab0-a730-d14299f80f43/image.png)
- 원하는 재료를 검색하여 냉장고에 추가할 수 있습니다.

#### [카드뉴스 리스트]
![](https://velog.velcdn.com/images/um0114/post/02548eaa-6fe3-4491-b7b6-058f8ff1a3b1/image.png)
- 관리자가 업로드한 카드뉴스의 목록을 볼 수 있습니다.

#### [카드뉴스 상세]
![](https://velog.velcdn.com/images/um0114/post/198d2b09-7812-4a3c-acb1-73d5641a8d16/image.png)
- 관리자가 작성한 카드뉴스의 상세 내용을 확인할 수 있습니다.

#### [챌린지 리스트]
![](https://velog.velcdn.com/images/um0114/post/6b02d4b0-87b5-48be-90d0-f25deb1f5e45/image.png)
- 관리자가 업로드한 챌린지의 목록을 볼 수 있습니다.

#### [챌린지 상세]
![](https://velog.velcdn.com/images/um0114/post/4087d310-8d02-4677-8f74-09c5f517c613/image.png)
- 관리자가 작성한 챌린지의 상세 내용을 확인할 수 있습니다.

#### [MY챌린지 인증 리스트]
![](https://velog.velcdn.com/images/um0114/post/6150393e-4b00-496f-aefb-055ee0ba6c9e/image.png)
- 인증한 글이 원하는 챌린지에 따라 저장됩니다.

#### [챌린지 관리]
![](https://velog.velcdn.com/images/um0114/post/55e00338-114e-449e-8ead-afe2035c505a/image.png)
- 진행 중, 완료, 미완료 상태의 챌린지가 표시됩니다.
- 진행 중인 챌린지에는 일차, 진행 중인 참여자 수, 유저의 인증 횟수가 나타납니다.

#### [MY챌린지 인증글 작성]
![](https://velog.velcdn.com/images/um0114/post/83f5ba5d-7a16-4f80-b8eb-2877c6a6294f/image.png)
- 참여 중인 챌린지에 대해 인증 사진을 포함한 글을 작성하여 제출합니다.

#### [MY챌린지 인증글 수정]
![](https://velog.velcdn.com/images/um0114/post/8551a85a-d0c3-4a77-8af8-f526eb433006/image.png)
- 이미 제출한 인증글을 다시 수정하여 내용을 변경하거나 추가할 수 있습니다.

#### [MY챌린지 인증글 상세]
![](https://velog.velcdn.com/images/um0114/post/5fbcc0c7-3b43-4232-adb2-3fb1f55d3876/image.png)
- 해당 인증글의 전체 내용을 확인할 수 있습니다.
- 다른 유저는 이 글에 댓글을 달 수 있습니다.

#### [1:1문의 리스트]
![](https://velog.velcdn.com/images/um0114/post/02d2127b-48db-4cb0-94d2-6f4f1fa9807c/image.png)
- 유저가 작성한 모든 1:1 문의 내역을 확인할 수 있습니다.

#### [1:1문의 등록]
![](https://velog.velcdn.com/images/um0114/post/752af19c-d3cd-4e14-adc3-574eaa8f5b67/image.png)
- 유저가 직접 문의 내용을 작성하여 제출할 수 있습니다.
- 문의가 등록되면 관리자가 이를 확인하고 답변을 제공할 수 있게 됩니다.

#### [1:1문의 수정]
![](https://velog.velcdn.com/images/um0114/post/69d3f3e9-5524-4a93-9672-8369547a845c/image.png)
- 유저가 이미 등록한 문의 내용을 수정할 수 있습니다.

#### [1:1문의 상세]
![](https://velog.velcdn.com/images/um0114/post/54dca05e-2bce-4259-9caa-8d5a09e928f5/image.png)
- 관리자가 유저의 문의 내용에 대한 답변을 작성할 수 있습니다.
- 유저와 관리자가 원활하게 소통하고 문의 처리 상태를 쉽게 파악할 수 있습니다.

### [관리자]

#### [대시보드]
![](https://velog.velcdn.com/images/um0114/post/795d38c7-3a93-48e0-a5d0-a6f5d772ab07/image.png)
- 유저의 활동(방문자 현황, 일자별 요약, 새 회원, 문의 내용)을 모니터링하고 원활한 서비스 운영을 지원할 수 있습니다.

#### [카드뉴스 작성]
![](https://velog.velcdn.com/images/um0114/post/e85fe552-72a8-4056-b260-1dcb3e8b5424/image.png)
- 건강 관련 카드뉴스를 작성할 수 있습니다.

#### [카드뉴스 리스트]
![](https://velog.velcdn.com/images/um0114/post/2afee5fb-b3ca-400a-9f4a-d3d2ad0edcc8/image.png)
- 카드뉴스 목록을 일반적인 게시판 페이지네이션 형식으로 보여줍니다.

#### [카드뉴스 상세]
![](https://velog.velcdn.com/images/um0114/post/f038af96-9dbe-43ec-986e-ca9b2f4844e8/image.png)
- 작성한 카드뉴스의 상세 내용을 확인할 수 있습니다.

#### [카드뉴스 수정]
![](https://velog.velcdn.com/images/um0114/post/1fb830ae-8ed2-4a80-8478-fb66d7f07b23/image.png)
- 관리자가 작성한 카드뉴스를 새로운 내용으로 수정할 수 있습니다.

#### [챌린지 작성]
![](https://velog.velcdn.com/images/um0114/post/c5d8331f-e725-42a6-8e4a-533fc9607306/image.png)
- 관리자가 유저가 진행할 챌린지들을 작성합니다.

#### [챌린지 리스트]
![](https://velog.velcdn.com/images/um0114/post/6b02d4b0-87b5-48be-90d0-f25deb1f5e45/image.png)
- 챌린지 목록을 일반적인 게시판 페이지네이션 형식으로 보여줍니다.

#### [챌린지 상세]
![](https://velog.velcdn.com/images/um0114/post/4087d310-8d02-4677-8f74-09c5f517c613/image.png)
- 작성한 챌린지의 상세 내용을 확인할 수 있습니다.

#### [챌린지 수정]
![](https://velog.velcdn.com/images/um0114/post/73977cf9-4951-4e94-b21b-889698481eac/image.png)
- 작성한 챌린지를 새로운 내용으로 수정할 수 있습니다.


## 📑 TripAngle 환경설정 가이드 북
### 1. 프로젝트 실행에 필요한 프로그램
[IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows)
[STS4(Google Drive)](https://drive.google.com/drive/u/1/folders/1q68H80Gg8GDYRe-ybOsmLJ4eAfMnbze4)
[SQLyog(Google Drive)](https://drive.google.com/drive/u/1/folders/1q68H80Gg8GDYRe-ybOsmLJ4eAfMnbze4)
[XAMPP](https://www.apachefriends.org/)


### 2. DB(DataBase) 세팅
1. HealthFoodLab 파일 내부의 db.sql 파일을 메모장으로 실행 후 텍스트 전체 복사(ctrl+A)해주세요.
   ![](https://velog.velcdn.com/images/um0114/post/3852f5e3-7860-4dee-afca-52a22688ca06/image.png)

2. 텍스트 붙여넣기 후 전체 쿼리 실행(F9)해주세요.
   ![](https://velog.velcdn.com/images/um0114/post/bcbbbab4-8eb3-480a-a4d6-c1686d66565a/image.png)
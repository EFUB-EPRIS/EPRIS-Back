# EPRIS-Back
# 기능 설명

이화여자대학교 pr 학회 epris 홈페이지 레포지토리입니다.

- pr 정보 확인
- presigned url 이용 파일 업로드
- Spring Security, JWT
- 관리자 페이지

# 기술 스택
|통합 개발 환경| IntelliJ                       |
|---|--------------------------------|
|Spring 버전| 3.3.2                          |
|데이터베이스| AWS RDS(MySQL)                 |
|배포| AWS EC2(Ubuntu),S3, CodeDeploy |
|Project 빌드 관리 도구| Gradle                         |
|CI/CD 툴| Github Actions                 |
|ERD 다이어그램 툴| ERD Cloud                      |
|Java version| Java 17                        |

# 기술 아키텍처

(사진 추가 예정)

# 팀원 소개 및 역할

| 이한나                                                                                                                 | 모수지                                                                                                  |
|---------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|
| <img src="https://avatars.githubusercontent.com/u/89291223?v=4"/>                                                   |  <img src="https://avatars.githubusercontent.com/u/108855492?v=4"/>                                                                                                      |
| [@hannah0226](https://github.com/hannah0226)                                                                        | [@nammsamm](https://github.com/nammsamm)                                                                     |
| - 프로젝트 세팅 <br> - 배포 및 CICD 설정 <br> - 관리자 페이지 로그인 기능 <br> - 기수 정보 수정 및 조회 <br> - 모집 정보 수정 및 조회 <br> - 그리팅 카드 CRUD <br> - 프로젝트 CRUD | - presigned url 이용 파일 업로드업로드 <br> - S3 파일 삭제 <br> - 로고 관련 기능 <br> - 학회원 관련 기능 <br> - 세션 관련 기능 <br> - 수상 이력 관련 기능 |

# ERD
![EPRIS_ERD](https://github.com/user-attachments/assets/c01a89c2-a81a-4d88-8a8b-088e349c6071)

# API명세서
![로그인API](https://github.com/user-attachments/assets/d7685976-becb-4c59-8092-580a4c8d5632)
![기수정보API](https://github.com/user-attachments/assets/726540d9-af7a-4da6-82a4-3540adb445ed)
![모집정보API](https://github.com/user-attachments/assets/f841439e-6f5b-457c-abd3-37d71f7988b8)
![그리팅카드API](https://github.com/user-attachments/assets/b227739d-b187-47b7-a897-e0a0e09e9812)
![프로젝트API](https://github.com/user-attachments/assets/10a000e3-8d0c-4f06-8f17-482482521859)
![로고API](https://github.com/user-attachments/assets/4891cce7-ab60-4a79-a18b-1becc73e30ff)
![세션API](https://github.com/user-attachments/assets/3f59b86f-13fd-4544-8cbc-3f5c1c858e0e)
![협력프로젝트API](https://github.com/user-attachments/assets/fe790955-fdbe-4029-92a7-4dcea0fd49d3)
![네트워크API](https://github.com/user-attachments/assets/959d9e39-432a-418e-b3cf-8d58308851f7)
![학회원API](https://github.com/user-attachments/assets/28aaaedf-1001-43d7-a2ec-5f440d1c5bd0)

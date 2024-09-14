# EPRIS-Back
# 기능 설명
이화여자대학교 pr 학회 epris 홈페이지 레포지토리입니다.

- pr 정보 확인
- presigned url 이용 파일 업로드
- Spring Security, JWT 이용 로그인
- 관리자 페이지

# 기술 스택
|통합 개발 환경| IntelliJ                       |
|---|--------------------------------|
|Spring Boot 버전| 3.3.2                          |
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
![로그인](https://github.com/user-attachments/assets/0235aeae-cbfd-4ea8-a6f1-7c606a24a649)
![기수정보](https://github.com/user-attachments/assets/f340689e-4428-4c45-8775-f18c3ef0b6d1)
![모집정보](https://github.com/user-attachments/assets/12a87fb9-351d-4a1e-9afc-da0e57530055)
![그리팅카드](https://github.com/user-attachments/assets/a6fefc60-c083-4744-a090-1d1cea158209)
![프로젝트](https://github.com/user-attachments/assets/fd6459ac-5412-4e5b-b819-888ed29156ce)
![로고](https://github.com/user-attachments/assets/4babe7b7-69c3-47cf-975f-cd91f16c2a7b)
![세션](https://github.com/user-attachments/assets/1234a0b3-e1fe-464e-9721-b5d71696acfd)
![협력프로젝트](https://github.com/user-attachments/assets/0cf77e35-94b5-4aa1-9140-7bb97e697e79)
![네트워크](https://github.com/user-attachments/assets/9cd69f41-31f1-483c-bdaa-a506235b29dd)
![학회원](https://github.com/user-attachments/assets/05274081-5481-4a82-9d1d-730ff5993392)


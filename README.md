# 프로젝트 개요
<aside>
💡 참석자 : 박규민 외 4인
</aside>

## 암호화

- **BCrypt (비크립트)**: 패스워드 암호화 방식으로 사용됩니다.

## Session VS Token

- [JWT Token](https://www.notion.so/JWT-50b811d52c46408da2b2dd44a512c357?pvs=21)

### 개발 단계에서 세션을 사용하는 것이 유리한 경우

- 애플리케이션이 상태를 유지하는 서버 기반의 전통적인 웹 애플리케이션일 때.
- 빠르게 프로토타입을 개발하고 테스트하는 과정에서 단순한 인증 방식이 필요할 때.

### 개발 단계에서 JWT를 사용하는 것이 유리한 경우

- 프로덕션 환경에서 JWT를 사용할 계획이 있고, 개발 단계부터 동일한 환경에서 개발 및 테스트를 진행하고자 할 때.
- SPA나 마이크로서비스 아키텍처와 같은 무상태 애플리케이션을 개발할 때.

## Javascript 라이브러리

- [SweetAlert2](https://sweetalert2.github.io/): 알림창 라이브러리.

## NEXT

- Swagger
- 브랜치 생성 후 form 제출까지 완료
- Thymeleaf 폴더 구조 (fragment 적용)


## Git & Jira 설정

[Git 및 GitFlow 설정 문서](https://www.notion.so/git-gitFlow-with-jira-69102d10504940afafee14fe6230b69d?pvs=21)

### NEXT

- 화면 설계서 완료 - 박규민, 이윤서, 황지원
- 코드 예시 작성 (회원가입) - 김성민, 이진주

## DB Diagram

[DB 다이어그램 보기](https://dbdiagram.io/d/66c28e978b4bb5230e6cfb76)

## 기능 Diagram

[기능 다이어그램 보기](https://app.diagrams.net/#G1HAbd9MUhMRY4PKPnCbenE_zWX8yeSvYH#{"pageId"%3A"C5RBs43oDa-KdzZeNtuy"})

## 구축 서비스 선택

- 서버 정보를 한 곳에 볼 수 있는 페이지

## 스택

> OS: Rocky 8.x  
> DB: PostgreSQL(JPA사용)  
> Language: Kotlin  
> Framework: Spring Boot  
> 소스 관리: Git  
> Template: [Bootstrap](https://themes.getbootstrap.com/)

## 주요 기능 정리

### Admin

- 유저 관리
    - 회원 가입
    - 회원 정보 수정
    - 회원 탈퇴

### User

- 회원가입
    - 관리자가 등록
    - `~~Firebase authentication` 도입 해볼까…고민 중~~
- 로그인
    - otp 구현 (추후 작업)
- **필요 데이터**
    - user_id
    - password
    - department
    - position
    - email
    - phone
    - level
    - otp 인증 (추후 작업)

### Board

서버 / 업체 / 서비스 따로 >> 매칭

### Server

- IP 주소
- 포트
- 접속 정보 (여러 개 입력 가능)
    - root
    - 다른 user도 있을 수 있음
- 디비 접속 정보 (여러 개 입력 가능)
    - 데이터베이스가 여러 개일 수 있음
- 작성자 / 수정자
- 작성일자 / 수정일자

### Customer

- 업체명
- 업체 담당자 (여러 개 입력 가능)
- 업체 담당자 연락처 (여러 개 입력 가능)
- 업체 유형 (관공서 / 학교 / 일반 기업 / 자체)
- 작성자 / 수정자
- 작성일자 / 수정일자

### Service

- 도메인 주소
- 도메인 구입처
- 인증서 정보 (SSL or 앱 갱신)
    - 갱신 일자
    - 인증서 구매처
- 작성자 / 수정자
- 작성일자 / 수정일자

### Code

- 업체 유형
- 도메인 구입처

## 폴더 구조 예시

```kotlin
my-springboot-app/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── myapp/
│   │   │               ├── MySpringBootApplication.kt
│   │   │               ├── **controller/**
│   │   │               ├── **service/**
│   │   │               ├── **repository/**
│   │   │               ├── **model/**
│   │   │               └── **config/**
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   └── templates/
│   │   └── webapp/
│   │       └── WEB-INF/
│   └── test/
│       └── kotlin/
│           └── com/
│               └── example/
│                   └── myapp/
├── .gitignore
├── build.gradle.kts or pom.xml
└── README.md

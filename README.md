

# 카카오뱅크 과제  -  박미솔
### 오픈 API를 이용하여 "블로그 검색 서비스"를 제공합니다.

## 1. 개발 환경
 -  IDE: IntelliJ IDEA 2020.3.2 (Ultimate Edition)
 -  OS: macOS Big Sur 11.2.3

## 의존성
 -  Spring Boot 3.2.0
 -  Spring Boot Starter Data JPA
 -  Spring Boot Starter Web
 -  Jackson Module Kotlin
 -  Spring Boot Starter Webflux
 -  Spring Boot Starter Validation
 -  Springdoc OpenAPI
 -  HikariCP
 -  Lombok
 -  H2 Database
 -  Spring Boot Devtools
 -  Spring Boot Starter Test

## 2. 사용 방법

### 실행 방법

1. 프로젝트를 다운로드 (git clone) 합니다.
2. IDE에서 프로젝트를 열거나 터미널에서 프로젝트 디렉터리로 이동합니다.
3. `./gradlew bootRun` 명령을 사용하여 애플리케이션을 실행합니다.

### API 문서
 -  API 문서 명세는 Swagger를 사용하여 제공합니다.
 -  애플리케이션 실행 후 `http://localhost:8081/swagger - ui/index.html` 로 접속해서 확인 가능합니다.

### 테스트 페이지
 -  간단한 개발자 테스트용 페이지를 제공합니다.
> 검색어 입력 후 검색 버튼 클릭 시 검색 결과를 확인할 수 있습니다. (인풋박스에서 엔터로 검색 가능)
> 실시간 (1초 단위)로 검색어 순위를 확인할 수 있습니다.
 -  애플리케이션 실행 후 `http://localhost:8081/main.html` 로 접속해서 확인 가능합니다.

## 3. 요구사항
1. 블로그 검색
 - [O] 키워드를 통해 블로그를 검색할 수 있어야 합니다.
 - [O] 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원해야 합니다.
 - [O] 검색 결과는 Pagination 형태로 제공해야 합니다.
 - [O] 검색 소스는 카카오 API의 키워드로 블로그 검색(https://developers.kakao.com/docs/latest/ko/daum - search/dev - guide#search - blog)을 활용합니다.
 - [O] 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려해야 합니다.

2. 인기 검색어 목록
 - [O] 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.
 - [O] 검색어 별로 검색된 횟수도 함께 표기해 주세요.

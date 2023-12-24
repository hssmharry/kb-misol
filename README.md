

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
> 혹은 jar 파일을 다운로드 합니다.
> 구글 드라이브 : https://drive.google.com/file/d/1FZ5UVUv4LVkssLskFTv3MWMCHaQXDYtU/view?usp=sharing

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
 > http://44.203.69.233:8081/main.html : 간단한 테스트 페이지
 > http://44.203.69.233:8081/swagger-ui/index.html : API 명세 확인

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

### 4. 번외.. (하고싶었지만 다 구현하지 못한 것)
1. 인기검색어 노출
 - 트래픽이 많은 경우를 위해서 해당 API를 캐싱처리. (반드시 실시간 반영이 되지 않아도 된다는 기획 요건을 충족한다면)
   - redis에 일정주기로 키워드별 카운트 횟수를 저장해두는 배치를 구현
   - 검색할 때는 redis를 우선 탐색 -> 없으면 db 조회 후 redis에 캐싱처리하는 방식
   - 조금 더 실시간 유효성을 맞추기 위해서 조회했을 때 db에도 저장하고 redis에도 카운트 횟수를 업데이트해주는 방식 고려. (횟수에 조금의 오차가 있어도 된다는 가정하에)
   
2. 검색 시 인기검색어 저장하는 부분 비동기로 추출
 - service search 메소드 내에 있는 log저장하는 부분을 kafka 혹은 적어도 event listener로 (async 붙여서) 처리
   - 검색하는 부분과 검색어 카운트 로그를 저장하는 로직은 사실 별개의 로직이라고 생각
   - 로그를 저장하는데에서 오류가 발생했다고해서 검색 응답값을 에러로 내려주는 것은 비 이상적인것 같음
   - 이 부분을 비동기처리 한다면 검색 속도도 더 빠르게 될것임
 - 코루틴으로 각각의 작업 (외부 api 호출 + 로그 저장)을 동시에 요청하는 (deffered) 것도 고려

3. 검색 결과 내재화 (만약 기획 요건과 충돌하지 않는다면)
 - 매번 외부와의 통신을 하는 부분이 가장 비용이 클 것으로 생각되기 때문에 일정 시간 내에 같은 형식의 요청을 받는 다면 캐싱처리 혹은 db에 저장해둔 값으로 리턴해주기.



# 감사합니다!!!

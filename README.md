# 일정 관리 조회 V1

# API 명세
- 하기 사항들은 전부 요청에 관련된 사항
- 응답 사항은 하단 API 실행 결과 (Postman 사용) 링크에 첨부
### 일정 생성하기
- URL : /schedules
- HTTP Method : POST
- Headers
  - Content-Type : application/json
- 예시
    ```json
      {
        "title":"TEST"
        , "content":"testing"
        , "author":"test1"
        , "pwd":1234
      }    
  ``` 

### 일정 개별 조회
- URL : /schedules/{id}
- HTTP Method : GET
- 예시
    ```json
      {
        "title":"TEST"
        , "content":"testing"
        , "author":"test1"
        , "pwd":1234
      }    
  ``` 

# API 실행 결과
https://documenter.getpostman.com/view/6724426/2sBXVbGtem

# ERD
<img width="403" height="424" alt="image" src="https://github.com/user-attachments/assets/3396633f-652f-4d5d-93ba-9487fb1d4d5f" />

작성 중


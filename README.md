# 일정 관리 조회 API V1 - 외래키 버전

# API 명세
- 모든 응답은 application/json 타입 사용

### 일정 생성하기
- URL : /schedules
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 201 Created
- Description
  - 제목, 내용, 비밀번호, 작성자는 필수값
  - 제목은 30자 이내, 내용은 200자 이내


- 요청 예시
```json
{
  "title":"TEST"
  , "content":"testing"
  , "author":"test1"
  , "pwd":1234
}    
``` 
- 응답 예시
```json
{
  "id": 1,
  "title": "TEST",
  "content": "testing",
  "author": "test1",
  "createAt": "2025-12-29T20:58:54.179598",
  "updateAt": "2025-12-29T20:58:54.179598"
}
```

### 일정 개별 조회
- URL : /schedules/:id
- HTTP Method : GET
- Response HTTP Status : 200 OK


- 응답 예시
```json
{
  "id": 1,
  "title": "TEST",
  "content": "testing",
  "author": "test",
  "createAt": "2025-12-29T19:32:19.631317",
  "updateAt": "2025-12-29T19:32:19.631317",
  "commentResponseList": []
}    
``` 

### 일정 전체 조회
- URL : /schedules
- HTTP Method : GET
- Query Parameter : author
- Response HTTP Status : 200 OK


- 전체 조회 예시
```
url : /schedules
```
```json
[
  {
      "id": 1,
      "title": "TEST",
      "content": "testing",
      "author": "test1",
      "createAt": "2025-12-29T20:58:54.179598",
      "updateAt": "2025-12-29T20:58:54.179598",
      "commentResponseList": []
  },
  {
      "id": 2,
      "title": "TEST",
      "content": "testing",
      "author": "test2",
      "createAt": "2025-12-29T20:59:29.342593",
      "updateAt": "2025-12-29T20:59:29.342593",
      "commentResponseList": []
  }
]    
``` 

- 작성자 별 조회 예시
```
url : /schedules?author=test1
```
```json
[
  {
    "id": 1,
    "title": "TEST",
    "content": "testing",
    "author": "test1",
    "createAt": "2025-12-29T20:58:54.179598",
    "updateAt": "2025-12-29T20:58:54.179598",
    "commentResponseList": []
  }
]    
``` 

### 일정 수정하기
- URL : /schedules/:id
- HTTP Method : PUT, PATCH
- Content-Type : application/json
- Response HTTP Status : 200 OK
- Description
  - 입력된 pwd와 검증 단계 있음
  - 제목과 작성자만 수정 가능
  - 제목은 30자 이내


- 요청 예시
```json
{
  "title":"TEST9"
    , "author":"test9"
    , "pwd":"1234"
}
```

- 성공 응답 예시
```json
{
  "id": 1,
  "title": "TEST9",
  "content": "testing",
  "author": "test9",
  "createAt": "2025-12-30T20:38:34.220021",
  "updateAt": "2025-12-30T20:39:35.500635"
}
```

- 실패 응답 예시
```json
{
  "errorType": "NOT_MATCH",
  "errorMessage": "등록한 일정의 비밀번호와 일치하지 않습니다"
}
```

### 일정 삭제하기
- URL : /schedules/:id
- HTTP Method : DELETE
- Content-Type : application/json
- Response HTTP Status : 204 No Content
- Description
  - 입력된 pwd와 검증 단계 있음


- 요청 예시
```json
{
  "pwd":1234
}
```

- 실패 응답 예시
```json
{
  "errorType": "NOT_MATCH",
  "errorMessage": "등록한 일정의 비밀번호와 일치하지 않습니다"
}
```

### 일정-댓글 생성하기
- URL : /schedules/:id/comments
- HTTP Method : POST
- Content-Type : application/json
- Response HTTP Status : 201 Created
- Description
  - 내용은 100자 이내


- 요청 예시
```json
{
  "content":"commetTesting"
  , "author":"commentTest"
  , "pwd":1234
}
```

- 응답 예시
```json
{
  "scheduleId": 1,
  "content": "commetTesting",
  "author": "commentTest",
  "createAt": "2025-12-30T20:25:18.012174",
  "updateAt": "2025-12-30T20:25:18.012174"
}
```
### 예외처리
- 미일치시 응답 처리
  - Response HTTP Status : 400 Bad Request
```json
{
  "errorType": "NOT_MATCH",
  "errorMessage": "등록한 일정의 비밀번호와 일치하지 않습니다"
}
```

- 조회 대상 미검출
  - Response HTTP Status :  404 Not Found
```json
{
  "errorType": "NOT_FOUND_DATA",
  "errorMessage": "존재하지 않는 일정입니다"
}
```


- 필수 값 미입력 또는 빈 값 일시
  - Response HTTP Status : 400 Bad Request
```json
{
  "errorType": "INPUT_DATA_WRONG",
  "errorMessage": "작성자는 필수입니다"
}
```

- 서버 오류 발생시
  - Response HTTP Status : 500 Bad Request
```json
{
  "errorType": "UNCONTROLLED_ERROR",
  "errorMessage": "서버 오류가 발생하였습니다"
}
```


# Entity ERD
<img width="415" height="426" alt="image" src="https://github.com/user-attachments/assets/47c93c69-8c53-4a12-a7d7-fe5444732c61" />

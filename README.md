# 지하철 어플리케이션 - 회원관리, 즐겨찾기

## 기능 목록

### 회원 관리

1.  회원 가입
- [ ] 이메일 형식이 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
- [ ] 이름이 입력되지 않았거나, 공백이 포함된 경우 예외 발생 (클라이언트, 서버)
- [ ] 비밀번호의 길이가 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
- [ ] password 확인 필드가 password 필드와 동일한 입력값을 가지지 않는 경우 (클라이언트, 서버)
- [ ] password 저장시 해시 알고리즘을 사용하여 단방향 암호화를 적용한다.

2.  로그인
- [ ] 이메일 형식이 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
- [ ] 비밀번호의 길이가 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
- [ ] 이메일이 존재하지 않은 경우 예외 발생 (서버)
- [ ] 비밀번호가 일치하지 않는 경우 예외 발생 (서버)

3.  로그인 후 회원정보 조회/수정/삭제
- 조회
    - [ ] 조회 화면에서는 비밀번호를 출력하지 않는다.
- 수정
    - [ ] 수정 화면에서 비밀번호를 바꾸지 않고 이메일 또는 이름을 수정할 수 있다.
    - [ ] 이메일 형식이 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
    - [ ] 이름이 입력되지 않았거나, 공백이 포함된 경우 예외 발생 (클라이언트, 서버)
    - [ ] 비밀번호의 길이가 올바르지 않은 경우 예외 발생 (클라이언트, 서버)
    - [ ] password 확인 필드가 password 필드와 동일한 입력값을 가지지 않는 경우 (클라이언트, 서버)
    - [ ] password 저장시 해시 알고리즘을 사용하여 단방향 암호화를 적용한다.
- 삭제
    - [ ] 서버에 존재하는 사용자 정보를 제거한다.

### 즐겨찾기

1. 즐겨찾기 추가
2. 즐겨찾기 목록조회 / 제거
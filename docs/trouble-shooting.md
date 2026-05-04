# 트러블 슈팅

## 문제1
- DBeaver에서 MySQL DB 연결 불가 'Public Key Retrieval is not allowed 에러' 발생

## 원인
- MySQL 8.0 버전 이후부터 발생하는 문제로 확인

## 해결
DBeaver에서 연결하고자 하는 DB 우클릭 > Edit Connection
 > Drvier properties > 'allowPublicKeyRetrieval' 값 false → true로 변경

-----------------------------------------------------------------------

## 문제2
- DaoAuthenticationProvider() 기본 생성자 사용 불가

## 원인
- Spring Boot 4.x / Spring Security 최신 버전에서는 DaoAuthenticationProvider를 기본 생성자로 만들 수 없음

## 해결
- 생성자에 UserDetailsService를 넣어야 함

-----------------------------------------------------------------------

## 문제3
- Content 상세 페이지 접근 시, Name for argument of type [java.lang.Long] not specified 에러 발생

## 원인
- 빌드 설정에서 파라미터 이름 정보가 남아있지 않아서 contentId를 읽어오지 못하므로 매핑 실패

## 해결
- ContentController 에서 @PathVariable 뒤에 ("contentId") 명시를 통해 Spring이 파라미터 이름을 추론하지 않도록 수정
- 컴파일 옵션에 덜 의존해서 안정성 향상



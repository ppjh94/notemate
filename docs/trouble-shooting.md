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

-----------------------------------------------------------------------

## 문제4
- 등록 페이지 접근 시, 컨트롤러 메서드에서 빈 객체(DTO)를 생성하여 View에 넘기는 과정에 boolean 값이 Null인 상태로 인한 에러 발생

## 원인
- Spring MVC의 데이터 바인딩(Data Binding) 메커니즘과 원시 타입(Primitive Type)의 특성으로 인해 발생

## 해결
- DTO에 원시 타입으로 선언 되어있는 boolean을 래퍼 클래스 형태인 Boolean으로 수정

-----------------------------------------------------------------------

## 문제5
- DB 테이블인 subscriptionPlan 매핑 실패로 인한 구독 플랜 확인 불가
- 화면에서는 표시되지만 구독하기 기능 실행 시 (plan_id) FK 외래키 참조 실패 에러 발생

## 원인
- Spring Boot + Hibernate에서 snake_case 네이밍으로 DB를 자동 생성 후 해당 테이블에 매핑하여 subscription_plan 테이블에 데이터가 저장되고 있었음
- subscriptionPlan 테이블과 연결되어있는 기존 코드로 인해 데이터가 없는 해당 테이블에서 planId 값을 찾아서 발생한 것으로 확인

## 해결
- DB 네이밍 규칙을 subscription_plan 으로 지정하고 그 외 테이블 삭제 및 매핑 코드 일부 수정 



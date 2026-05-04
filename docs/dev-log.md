# 작업 로그

## BaseTimeEntity 생성
 - createdAt, updatedAt 공통 관리용

## Entity 작성
 - DB 테이블과 매핑되는 객체
 - JPA가 관리하는 핵심 도메인 객체
 - 테이블 구조를 반영, 상태와 연관관계 표현
 - "데이터 + 상태 변화 메서드"의 중심

## Config
 - 전역 설정
 - Security, JPA Auditing, MVC, CORS, Bean 등
 - 프로젝트 전체 동작 방식을 정하는 곳
 
## JpaAuditingConfig 추가
 - BaseTimeEntity의 @CreatedDate, @LastModifiedDate에 감사(Auditing) 기능을 켜서 생성/수정 시작을 자동 관리하는 역할 (공통 시간 관리를 위해 코드를 줄여주고 실수를 막을 수 있음)

## Entity에 대한 Repository 작성
 - Entity 조회/저장/수정/삭제를 할 수 있게하는 역할
 - JPA 인터페이스 정의

## DTO 추가
 - 데이터 전달 객체
 - 요청값 받기, 응답값 내보내기
 - Entity를 직접 노출하지 않기

## Controller 추가
 - 요청을 받는 곳 (브라우저/클라이언트와 가장 가까운 계층)
 - URL 요청 받기, 서비스 호출
 - View 이름 반환 or JSON 반환
 - 검증된 요청을 서비스에 넘기는 역할 중심

## Service 추가
 - 비지니스 로직
 - 여러 Repository 조합
 - 트랜잭션 처리
 - 핵심 규칙 구현
 


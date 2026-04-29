# 작업 로그

## BaseTimeEntity 생성
 - createdAt, updatedAt 공통 관리용

## Entity 작성
 - DB 테이블과 매핑되는 객체
 - JPA가 관리하는 핵심 도메인 객체
 - 테이블 구조를 반영, 상태와 연관관계 표현
 
## JpaAuditingConfig 추가
 - BaseTimeEntity의 @CreatedDate, @LastModifiedDate에 감사(Auditing) 기능을 켜서 생성/수정 시작을 자동 관리하는 역할 (공통 시간 관리를 위해 코드를 줄여주고 실수를 막을 수 있음)

## DB 테이블 Entity에 대한 Repository 작성
 - Entity를 DB에서 조회/저장/수정/삭제할 수 있게하는 역할
 - JPA 인터페이스 정의


# 작업 로그

## 2026-03-31
 - 프로젝트 주제 확정
 - 기능 명세 초안 작성
 - Github Repository 생성 후 Spring boot 프로젝트 연결
 
## 2026-04-01
 - ERD 구조 설계 및 ERD Cloud 작업 완료
 - 프로젝트 패키지 구조 설계
 - docs 폴더 생성 및 초안 작성
 - README.md 생성
 
## 2026-04-02
 - MySQL 재설치 및 DBeaver 연결 테스트 성공
 - DDL 정의 및 테이블 생성 완료
 
## 2026-04-03
 - application-secret.yaml 분리하여 gitignore 등록
 - JPA Entity 클래스에 대한 패키지 구조 설계
 - BaseTimeEntity 생성
 
## 2026-04-05
 - 전체 테이블에 대한 Entity 작성 완료
 
## 2026-04-06
 - JpaAuditingConfig 추가 
 JpaAuditingConfig의 역할: BaseTimeEntity의 @CreatedDate, @LastModifiedDate에 감사(Auditing) 기능을 켜서 생성/수정 시작을 자동 관리하는 역할 (공통 시간 관리를 위해 코드를 줄여주고 실수를 막을 수 있음)
 - User, Content에 대한 Repository 작성

## 2026-04-07
 - DB 테이블 Entity에 대한 Repository 작성 완료
 Entity를 DB에서 조회/저장할 수 있게하는 역할

## 2026-04-09

 
# 트러블 슈팅

## 문제
- DBeaver에서 MySQL DB 연결 불가 'Public Key Retrieval is not allowed 에러' 발생

## 원인
- MySQL 8.0 버전 이후부터 발생하는 문제로 확인

## 해결
DBeaver에서 연결하고자 하는 DB 우클릭 > Edit Connection
 > Drvier properties > 'allowPublicKeyRetrieval' 값 false → true로 변경
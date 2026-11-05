# 데이터베이스 설계 프로젝트

DB 스키마를 DBML로 정의하고 DDL로 생성하는 프로젝트

## 타겟 데이터베이스
- PostgreSQL

## 데이터베이스 연결 정보

### PostgreSQL

- 호스트 | `localhost` |
- 포트 | `5432` |
- 사용자 | `postgres` |
- 비밀번호 | `postgres` | 


### Oracle

- 호스트 | `localhost` |
- 서비스명 | `orcl` |
- 포트 | `1521` |
- 사용자 | `oracle` |
- 비밀번호 | `oracle` | 


## VS Code 확장 설치

- dbdiagram | dbdiagram | DBML 편집 및 ERD 미리보기
- PostgreSQL | Microsoft | PostgreSQL 서버 연결, 스키마 조회 및 SQL 실행
- Oracle SQL Developer for VSCode | Oracle corporation | Oracle 서버 연결, 스키마 조회 및 SQL 실행



## 파일 구성

- `schema.dbml` | 테이블, 컬럼, 제약조건 및 관계를 정의하는 ERD 원본
- `schema.sql` | 테이블 생성 및 주석 DDL
- `schema.dbdiagram` | ERD 다이어그램 배치 설정 데이터


## ERD 미리보기

- VS Code에서 `schema.dbml`을 열니다.
- ERD 미리보기를 하려면 우측 상단에서 Text Editor를 확장해서 `DBML Diagram Preview`를 선택합니다.

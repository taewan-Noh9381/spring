-- 회원관련 테이블
CREATE TABLE HAPPYMEMBER(
	ID VARCHAR2(50),
	NAME VARCHAR2(20),
	PW VARCHAR2(30),
	AUTH CHAR(1),
	DELFLAG CHAR(1),
	REGDATE DATE
);

ALTER TABLE HAPPYMEMBER ADD CONSTRAINT HAPPYMEMBER_PK PRIMARY KEY (ID);

--로그인
SELECT ID, NAME, PW, AUTH, DELFLAG, REGDATE
	FROM HAPPYMEMBER
	WHERE ID = 'USER01' AND PW = '1234';
--회원전체조회
SELECT ID, NAME, PW, AUTH, DELFLAG, TO_CHAR(REGDATE, 'YYYY. MM. DD.') 
	FROM HAPPYMEMBER;
--회원가입
INSERT INTO HAPPYMEMBER (ID, NAME, PW,
						AUTH, DELFLAG, REGDATE)
			VALUES ('USER01', '사용자1', '1234',
					'U', 'N', SYSDATE);
INSERT INTO HAPPYMEMBER (ID, NAME, PW,
						AUTH, DELFLAG, REGDATE)
			VALUES ('ADMIN01', '관리자1', '1234',
					'A', 'N', SYSDATE);

--아이디중복체크
SELECT COUNT(*)
	FROM HAPPYMEMBER h 
	WHERE ID = 'USER01';
--회원전체(사용)조회
SELECT ID, NAME, PW, AUTH, DELFLAG, REGDATE
	FROM HAPPYMEMBER
	WHERE ID = 'USER01' AND PW = '1234' AND DELFLAG = 'N';
--패스워드 조회
SELECT PW
	FROM HAPPYMEMBER h 
	WHERE ID = 'USER01';
--아이디로 정보 조회 로그인
SELECT ID, NAME, AUTH, REGDATE
	FROM HAPPYMEMBER h 
	WHERE ID = 'USER01';
--사용자 상태정보 변경
UPDATE HAPPYMEMBER SET DELFLAG = 'Y' 
	WHERE ID = 'USER01';


--------------2021-11-29 aop
-- Transaction 처리
-- 답글을 작성할 때 선택된 row값보다 큰 step을 +1씩 증가 성공 -> 답글 insert가 되어야 함
-- true / false => rollback
-- false / true => rollback
-- 즉, 업데이트 혹인 인서트가 실패하면 모두 rollback

SELECT *
	FROM ANSWERBOARD a 
	ORDER BY REFER DESC, STEP ASC;

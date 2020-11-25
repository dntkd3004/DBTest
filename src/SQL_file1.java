# 기존에 t1 데이터베이스가 존재 한다면 삭제
DROP DATABASE IF EXISTS t1;

# 새 데이터베이스(`t1`) 생성
CREATE DATABASE t1;

USE t1;

# 테이블 생성시 주키(primary key) 등록
# 자동 증가 제약 (AUTO_INCREMENT) 추가
CREATE TABLE article(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
title CHAR(100) NOT NULL,
`body` TEXT NOT NULL,
nickname CHAR(50) NOT NULL,
hit INT(5) NOT NULL
);

# id에 unsigned 제약 걸기
ALTER TABLE article MODIFY id INT(5) UNSIGNED NOT NULL AUTO_INCREMENT;

# 테스트 데이터 추가
INSERT INTO article
SET title = '제목1',
`body` = '내용1',
nickname = '유저1',
hit = 20;

INSERT INTO article
SET title = '제목2',
`body`= '내용2',
nickname = '유저2',
hit = 30;

INSERT INTO article
SET title = '제목3',
`body` = '내용3',
nickname = '유저3',
hit = 10;

# 데이터 추가
INSERT INTO article
SET title = '제목4',
`body` = '내용4',
nickname = '유저4',
hit = 55;

# 데이터 추가
INSERT INTO article
SET title = '제목5',
`body` = '내용5',
nickname = '유저5',
hit = 10;

# 데이터 추가
INSERT INTO article
SET title = '제목6',
`body` = '내용6',
nickname = '유저6',
hit = 100;

SELECT *
FROM article;

DROP DATABASE IF EXISTS s1;

CREATE DATABASE s1;

USE s1;

CREATE TABLE t_order(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
userNo INT(5) NOT NULL,
productNo INT(5) NOT NULL
);

CREATE TABLE t_user(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
userId CHAR(200) NOT NULL,
userPw CHAR(200) NOT NULL,
userName CHAR(50) NOT NULL,
addr CHAR(200) NOT NULL
);

CREATE TABLE t_product(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
pname CHAR(100) NOT NULL,
price INT(10) NOT NULL
);


INSERT INTO t_product
SET pName = '운동화',
price = 1000000;

INSERT INTO t_product
SET pName = '코트',
price = 100000;

INSERT INTO t_product
SET pName = '반바지',
price = 30000;

INSERT INTO t_product
SET pName = '스커트',
price = 15000;

INSERT INTO t_product
SET pName = '코트',
price = 100000;

INSERT INTO t_product
SET pName = '티셔츠',
price = 9000;

INSERT INTO t_product
SET pName = '운동화',
price = 200000;

INSERT INTO t_product
SET pName = '모자',
price = 30000;



INSERT INTO t_user
SET userId = 'user1',
userPw = 'pass1',
userName = '손흥민',
addr = '런던';

INSERT INTO t_user
SET userId = 'user2',
userPw = 'pass2',
userName = '설현',
addr = '서울';

INSERT INTO t_user
SET userId = 'user3',
userPw = 'pass3',
userName = '원빈',
addr = '대전';

INSERT INTO t_user
SET userId = 'user4',
userPw = 'pass4',
userName = '송혜교',
addr = '대구';

INSERT INTO t_user
SET userId = 'user5',
userPw = 'pass5',
userName = '소지섭',
addr = '부산';

INSERT INTO t_user
SET userId = 'user6',
userPw = 'pass6',
userName = '김지원',
addr = '울산';

INSERT INTO t_order
SET userNo = 1,
productNo = 1;

INSERT INTO t_order
SET userNo = 2,
productNo = 2;

INSERT INTO t_order
SET userNo = 3,
productNo = 3;

INSERT INTO t_order
SET userNo = 4,
productNo = 4;

INSERT INTO t_order
SET userNo = 5,
productNo = 5;

INSERT INTO t_order
SET userNo = 6,
productNo = 6;

INSERT INTO t_order
SET userNo = 6,
productNo = 7;

INSERT INTO t_order
SET userNo = 1,
productNo = 5;

INSERT INTO t_order
SET userNo = 4,
productNo = 4;

INSERT INTO t_order
SET userNo = 1,
productNo = 1;

INSERT INTO t_order
SET userNo = 5,
productNo = 8;

SELECT *
FROM t_product;

SELECT *
FROM t_user;

SELECT *
FROM t_order;

# 1. 손흥민의 주문 개수는? 3개
SELECT id FROM t_user WHERE userName = '손흥민';
SELECT COUNT(*) cnt FROM t_order WHERE userNo = 1;
# 2. 손흥민이 산 상품은? 운동화 2개, 코트 1개
SELECT productNo FROM t_order WHERE userNo = 1;
SELECT * FROM t_product WHERE id IN (1, 5);
# distinct, group by
# 3. 스커트를 산 사람은? 송혜교
SELECT id FROM t_product WHERE pname = '스커트';
SELECT DISTINCT userNo FROM t_order WHERE productNo = 4;
SELECT userNo FROM t_order WHERE productNo = 4 GROUP BY userNo;
SELECT * FROM t_user WHERE id = 4;
# 4. 가장 많이 주문한 사람의 아이디와 이름, 주문개수는? user1, 손흥민, 3개
SELECT userNo, COUNT(userNo) cnt FROM t_order GROUP BY userNo ORDER BY cnt DESC;
SELECT * FROM t_user WHERE id = 1;
# 5. 소지섭이 사용한 총 금액은? 130,000원
SELECT id FROM t_user WHERE userName = '소지섭';
SELECT productNo FROM t_order WHERE userNo = 5;
SELECT SUM(price) price FROM t_product WHERE id IN (5, 8);

# 1. 손흥민의 주문 개수는? 3개
SELECT COUNT(*) cnt
FROM t_order o
INNER JOIN t_user u
ON o.userNo = u.id
WHERE u.userName = '손흥민';
# 2. 손흥민이 산 상품은? 운동화 2개, 코트 1개
SELECT p.pname, COUNT(*) cnt
FROM ((t_order o
INNER JOIN t_product p
ON o.productNo = p.id)
INNER JOIN t_user u
ON o.userNo = u.id)
WHERE u.userName = '손흥민'
GROUP BY p.pname;
# distinct, group by
# 3. 스커트를 산 사람은? 송혜교
SELECT u.userName, COUNT(*) cnt
FROM ((t_order o 
INNER JOIN t_user u
ON o.userNo = u.id)
INNER JOIN t_product p
ON o.productNo = p.id)
WHERE p.pname = '스커트'
GROUP BY u.userName;
# 4. 가장 많이 주문한 사람의 아이디와 이름, 주문개수는? user1, 손흥민, 3개
SELECT u.userId, u.userName, COUNT(*) cnt
FROM ((t_order o
INNER JOIN t_user u
ON o.userNo = u.id)
INNER JOIN t_product p
ON o.productNo = p.id)
GROUP BY u.userName
ORDER BY cnt DESC
LIMIT 1;
# 5. 소지섭이 사용한 총 금액은? 130,000원
SELECT SUM(p.price)
FROM ((t_order o
INNER JOIN t_user u
ON o.userNo = u.id)
INNER JOIN t_product p
ON o.productNo = p.id)
WHERE u.userName = '소지섭';

SELECT u.userName, SUM(p.price)
FROM ((t_order o
INNER JOIN t_user u
ON o.userNo = u.id)
INNER JOIN t_product p
ON o.productNo = p.id)
GROUP BY u.userName
HAVING u.userName = '소지섭';
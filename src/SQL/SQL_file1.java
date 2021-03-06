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

CREATE TABLE reply(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
aid INT(5) NOT NULL,
`body` CHAR(25) NOT NULL,
writer CHAR(50) NOT NULL,
regDate DATETIME
);

SELECT *
FROM reply;

ALTER TABLE article ADD regDate DATETIME NOT NULL AFTER hit;

CREATE TABLE `member` (
id INT(5) PRIMARY KEY AUTO_INCREMENT,
loginId CHAR(30) UNIQUE NOT NULL,
loginPw CHAR(50) NOT NULL,
nickname CHAR(50) NOT NULL,
regDate DATETIME
);

DESC `member`;

ALTER TABLE `member` MODIFY regDate DATETIME NOT NULL;

SELECT *
FROM `member`;

SELECT *
FROM `member` WHERE loginId = 'aaa' AND loginPw = 'bbb';

ALTER TABLE article DROP nickname;

SELECT * FROM (
SELECT a.*, m.nickname FROM article a INNER JOIN `member` m ON a.mid = m.id) aa;

SELECT *
FROM article;

ALTER TABLE article ADD `mid` INT(5) NOT NULL AFTER `body`;

SELECT *
FROM `member`;

UPDATE article SET `mid` = 1;
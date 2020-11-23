
public class SQL_File {
	# 기존에 t1 데이터베이스가 존재 한다면 삭제
	DROP DATABASE IF EXISTS t1;

	# 새 데이터베이스(`t1`) 생성
	CREATE DATABASE t1;

	USE t1;

	# 테이블 생성시 주키(primary key) 등록
	# 자동 증가 제약 (AUTO_INCREMENT) 추가
	CREATE TABLE article(
	id INT(5) PRIMARY KEY AUTO_INCREMENT,
	title CHAR(100),
	`body` TEXT
	);

	DESC article;

	# 나머지 모든 컬럼에 not null 제약 걸기
	ALTER TABLE article MODIFY title CHAR(100) NOT NULL;
	ALTER TABLE article MODIFY `body` TEXT NOT NULL;

	# id에 unsigned 제약 걸기
	ALTER TABLE article MODIFY id INT(5) UNSIGNED NOT NULL AUTO_INCREMENT;

	ALTER TABLE article ADD nickname CHAR(50) NOT NULL AFTER `body`;

	# hit 조회수 칼럼 추가.
	ALTER TABLE article ADD hit INT(5) NOT NULL;

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
}

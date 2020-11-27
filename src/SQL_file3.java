DROP DATABASE IF EXISTS scott;

CREATE DATABASE scott;

USE scott;

CREATE TABLE DEPT (
    DEPTNO DECIMAL(2),
    DNAME VARCHAR(14),
    LOC VARCHAR(13),
    CONSTRAINT PK_DEPT PRIMARY KEY (DEPTNO) 
);
CREATE TABLE EMP (
    EMPNO DECIMAL(4),
    ENAME VARCHAR(10),
    JOB VARCHAR(9),
    MGR DECIMAL(4),
    HIREDATE DATE,
    SAL DECIMAL(7,2),
    COMM DECIMAL(7,2),
    DEPTNO DECIMAL(2),
    CONSTRAINT PK_EMP PRIMARY KEY (EMPNO),
    CONSTRAINT FK_DEPTNO FOREIGN KEY (DEPTNO) REFERENCES DEPT(DEPTNO)
);
CREATE TABLE SALGRADE ( 
    GRADE TINYINT,
    LOSAL SMALLINT,
    HISAL SMALLINT 
);
INSERT INTO DEPT VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO DEPT VALUES (20,'RESEARCH','DALLAS');
INSERT INTO DEPT VALUES (30,'SALES','CHICAGO');
INSERT INTO DEPT VALUES (40,'OPERATIONS','BOSTON');
INSERT INTO EMP VALUES (7369,'SMITH','CLERK',7902,STR_TO_DATE('17-12-1980','%d-%m-%Y'),800,NULL,20);
INSERT INTO EMP VALUES (7499,'ALLEN','SALESMAN',7698,STR_TO_DATE('20-2-1981','%d-%m-%Y'),1600,300,30);
INSERT INTO EMP VALUES (7521,'WARD','SALESMAN',7698,STR_TO_DATE('22-2-1981','%d-%m-%Y'),1250,500,30);
INSERT INTO EMP VALUES (7566,'JONES','MANAGER',7839,STR_TO_DATE('2-4-1981','%d-%m-%Y'),2975,NULL,20);
INSERT INTO EMP VALUES (7654,'MARTIN','SALESMAN',7698,STR_TO_DATE('28-9-1981','%d-%m-%Y'),1250,1400,30);
INSERT INTO EMP VALUES (7698,'BLAKE','MANAGER',7839,STR_TO_DATE('1-5-1981','%d-%m-%Y'),2850,NULL,30);
INSERT INTO EMP VALUES (7782,'CLARK','MANAGER',7839,STR_TO_DATE('9-6-1981','%d-%m-%Y'),2450,NULL,10);
INSERT INTO EMP VALUES (7788,'SCOTT','ANALYST',7566,STR_TO_DATE('13-7-1987','%d-%m-%Y')-85,3000,NULL,20);
INSERT INTO EMP VALUES (7839,'KING','PRESIDENT',NULL,STR_TO_DATE('17-11-1981','%d-%m-%Y'),5000,NULL,10);
INSERT INTO EMP VALUES (7844,'TURNER','SALESMAN',7698,STR_TO_DATE('8-9-1981','%d-%m-%Y'),1500,0,30);
INSERT INTO EMP VALUES (7876,'ADAMS','CLERK',7788,STR_TO_DATE('13-7-1987', '%d-%m-%Y'),1100,NULL,20);
INSERT INTO EMP VALUES (7900,'JAMES','CLERK',7698,STR_TO_DATE('3-12-1981','%d-%m-%Y'),950,NULL,30);
INSERT INTO EMP VALUES (7902,'FORD','ANALYST',7566,STR_TO_DATE('3-12-1981','%d-%m-%Y'),3000,NULL,20);
INSERT INTO EMP VALUES (7934,'MILLER','CLERK',7782,STR_TO_DATE('23-1-1982','%d-%m-%Y'),1300,NULL,10);
INSERT INTO SALGRADE VALUES (1,700,1200);
INSERT INTO SALGRADE VALUES (2,1201,1400);
INSERT INTO SALGRADE VALUES (3,1401,2000);
INSERT INTO SALGRADE VALUES (4,2001,3000);
INSERT INTO SALGRADE VALUES (5,3001,9999);


# 사원 테이블의 모든 레코드를 조회하시오.
SELECT *
FROM emp;

#사원명과 입사일을 조회하시오.
SELECT ename, hiredate
FROM emp;

#사원번호와 이름을 조회하시오.
SELECT empno, ename
FROM emp;

#사원테이블에 있는 직책의 목록을 조회하시오.
#distinct를 이용하는 방법과 group by를 이용하는 방법이 있다.
#distinct와 group by는 중복을 제거해주는 기능은 같지만 group by의 경우 정렬기능이 있고 집계함수를 사용하면
#그룹별로 집계할 수 있다.
SELECT DISTINCT job
FROM emp;

SELECT job
FROM emp
GROUP BY job;

#총 사원수를 구하시오.
#count() 함수는 조회된 행(row)의 개수를 알려준다.
SELECT COUNT(*) cnt
FROM emp;

#부서번호가 10인 사원을 조회하시오.
SELECT *
FROM emp
WHERE deptno = 10;

#월급여가 2500이상 되는 사원을 조회하시오.
SELECT *
FROM emp
WHERE sal >= 2500;

#이름이 'KING'인 사원을 조회하시오.
SELECT *
FROM emp
WHERE ename = 'king';

#사원들 중 이름이 S로 시작하는 사원의 사원번호와 이름을 조회하시오.
#임의의 문자가 포함된 것을 찾고자 할 때는 LIKE를 이용한다. % 기호를 이용해 찾고자 하는 문자의 위치를 정할 수 있다.
SELECT empno, ename
FROM emp
WHERE ename LIKE 'S%';

#사원 이름에 T가 포함된 사원의 사원번호와 이름을 조회하시오.
SELECT empno, ename
FROM emp
WHERE ename LIKE '%T%';

#커미션이 300, 500, 1400 인 사원의 사번,이름,커미션을 조회하시오.
#OR와 IN 두가지 방법을 사용할 수 있다.
SELECT empno, ename, comm
FROM emp
WHERE comm = 300 OR comm = 500 OR comm = 1400;

#IN()은 괄호 안에 대상들과 일치하는 값을 조회한다. 값의 개수가 1개 이상일 땐 ,를 구분자로 사용한다.
SELECT empno, ename, comm
FROM emp
WHERE comm IN (300, 500, 1400);

#월급여가 1200 에서 3500 사이의 사원의 사번,이름,월급여를 조회하시오.
#OR와 마찬가지로 AND나 BETWEEN을 사용하는 두가지 방법이 있다.
SELECT empno, ename, sal
FROM emp
WHERE sal >= 1200 AND sal <= 3500;

#BETWEEN A AND B 는 어떤 컬럼의 값이 A와 B 사이인지를 검사해준다.
SELECT empno, ename, sal
FROM emp
WHERE sal BETWEEN 1200 AND 3500;

#직급이 매니저이고 부서번호가 30번인 사원의 이름,사번,직급,부서번호를 조회하시오.
#조건이 2개 이상일 땐 AND나 OR로 연결할 수 있다. AND는 모든 조건이 맞아야 조회결과에 포함시키고, OR는 하나라도 맞는 조건이 있으면 조회결과에 포함한다.
SELECT ename, empno, job, deptno
FROM emp
WHERE job = 'MANAGER'
AND deptno = 30;

#부서번호가 30인 아닌 사원의 사번,이름,부서번호를 조회하시오.
#어떤 조건과 일치하지 않는 결과를 원할 때는 != 기호를 써서 같지 않음을 표현할 수 있다.
SELECT empno, ename, deptno
FROM emp
WHERE deptno != 30;

#커미션이 300, 500, 1400 이 모두 아닌 사원의 사번,이름,커미션을 조회하시오.
#조건이 여러개 일때는 위에서 했던 것처럼 모든 조건에 != 기호를 붙이고 AND나 OR로 연결해서 조회할 수 있다.
#또는 IN() 이나 BETWEEN A AND B 앞에 NOT이라는 반전 키워드를 사용할 수도 있다. 
#NOT을 사용하면 원래 조건에 해당하는 조회 결과의 반대로 보여준다.

SELECT empno, ename, comm
FROM emp
WHERE comm != 300 AND comm != 500 AND comm != 1400;

SELECT empno, ename, comm
FROM emp
WHERE NOT comm IN(300, 500, 1400);

#이름에 S가 포함되지 않는 사원의 사번,이름을 조회하시오.
#마찬가지로 LIKE앞에 NOT을 붙이면 기존의 결과의 반대 결과를 가져온다.
SELECT empno, ename
FROM emp
WHERE ename NOT LIKE '%S%';

#급여가 1200보다 미만이거나 3700 초과하는 사원의 사번,이름,월급여를 조회하시오.
#조건 2개를 연결하거나 NOT BETWEEN을 사용한다.
SELECT empno, ename, sal
FROM emp
WHERE sal < 1200 OR sal > 3700;

SELECT empno, ename, sal
FROM emp
WHERE sal NOT BETWEEN 1200 AND 3700;

#직속상사가 NULL 인 사원의 이름과 직급을 조회하시오.
#null인 데이터를 찾고자 하거나 거르고자 하면 각각 IS NULL, IN NOT NULL 키워드를 사용한다.
SELECT ename, job
FROM emp
WHERE mgr IS NULL;

#부서별 평균월급여를 구하는 쿼리
#부서별이기 때문에 deptno로 그룹핑하여 집계함수를 사용한다. AVG()는 평균을 구하는 함수
SELECT deptno, AVG(sal)
FROM emp
GROUP BY deptno;

#부서별 전체 사원수와 커미션을 받는 사원들의 수를 구하는 쿼리
#부서별이기 때문에 deptno로 그룹핑하여 집계함수를 사용한다. COUNT()는 조회된 행의 개수를 구하는 함수
SELECT deptno, COUNT(*) cnt, COUNT(comm) comm
FROM emp
GROUP BY deptno;

#부서별 최대 급여와 최소 급여를 구하는 쿼리
#부서별이기 때문에 deptno로 그룹핑하여 집계함수를 사용한다. MAX()는 최대값 MIN()은 최소값을 구하는 함수
SELECT deptno, MAX(sal), MIN(sal)
FROM emp
GROUP BY deptno;

#부서별로 급여 평균 (단, 부서별 급여 평균이 2000 이상만)
#부서별이기 때문에 deptno로 그룹핑하여 집계함수를 사용한다. AVG()는 평균을 구하는 함수.
#부서별급여 평균이 조건절에 들어갔기 때문에 그룹핑 이후에 조건을 따져야 한다. where는 group by 이후에는 사용할 수 없다.
#이렇게 그룹핑된 결과에서 조건을 따질 때는 where가 아닌 having을 이용한다. having은 group by 이후에 사용해야 한다.
SELECT  deptno, AVG(sal)
FROM emp
GROUP BY deptno
HAVING AVG(sal) >= 2000;

#월급여가 1000 이상인 사원만을 대상으로 부서별로 월급여 평균을 구하라.단, 평균값이 2000 이상인 레코드만 구하라.
#월급여가 1000 이상인 사원을 먼저 where로 추린다음에 부서별로 평균을 구한다. 부서별 평균값이 조건으로 쓰이고 있으므로
#HAVING을 이용해 GROUP BY된 결과를 다시 추릴 수 있다.
SELECT  deptno, AVG(sal)
FROM emp
WHERE sal >= 1000
GROUP BY deptno
HAVING AVG(sal) >= 2000;

#사원명과 부서명을 조회하시오.
#각각 다른 테이블의 정보를 봐야 하므로 조인을 이용한다. 조인을 할때는 각각의 컬럼의 소속을 테이블 이름으로 명시해야하는데
#이를 간편하게 하기 위해 테이블에 별명을 지어줄 수 있다. 테이블명에 공백을 한칸 주고 별명으로 쓸 문자를 적으면 된다. 아래에서는 각각 e와 d로 정해주었다
SELECT e.ename, d.dname
FROM emp e
INNER JOIN dept d
ON e.deptno = d.deptno;


#이름,월급여,월급여등급을 조회하시오.
#조인의 조건을 꼭 같다 아니다만 가지고 걸수 있는 건 아니다. 아래처럼 조인 조건 대상이 되는 컬럼의 값이 특정 범위를 가질 때는
#범위를 지정해주어 해당 범위 내에 속하는 행만 붙일 수 있다. 아래 조인 조건은 사원의 sal이 급여등급의 losal 값과 hisal 값 사이에 해당하는 등급만을 붙여주는 조인조건이다.
SELECT e.ename, e.sal, s.grade
FROM emp e
INNER JOIN salgrade s
ON e.sal BETWEEN s.losal AND s.hisal;

#이름,부서명,월급여등급을 조회하시오.
#부서명을 조회해야 하므로 dept 테이블을 하나 더 조인한다.
SELECT e.ename, d.dname, s.grade
FROM ((emp e
INNER JOIN dept d
ON e.deptno = d.deptno)
INNER JOIN salgrade s
ON e.sal BETWEEN s.losal AND s.hisal);

#이름,직속상사이름을 조회하시오.(self join)
#외부OUTER 조인. A LEFT JOIN B는 조인 조건에 만족하지 못하더라도 왼쪽 테이블 A의 행을 나타내고 싶을 때 사용한다.
#반대로 A RIGHT JOIN B는 조인 조건에 만족하지 못하더라도 오른쪽 테이블 B의 행을 나타내고 싶을 때
#mgr이 직속상사의 사번이다. 직속상사이름을 조회하라고 하였으므로 직속상사의 사번으로 사원의 정보를 조회해야 한다.
#이는 다시 한번 사원테이블의 정보가 필요하다는 의미이므로 두개의 사원테이블을 붙여서 봐야하는 경우이다. 이럴때 같은 테이블을 두개 조인하여
#조회하는 것을 셀프조인, 자기조인이라고 부르고 아래처럼 사용한다.
#같은 테이블을 사용하여 조인하는 것이므로 반드시 별명을 붙여주어야 한다. 사원의 이름을 조회할 emp1과 직속상관의 사번을 연결하여 직속상관의 사원정보를 조회할 emp2 테이블로 조인한다.
SELECT e1.ename, e2.ename
FROM emp e1
INNER JOIN emp e2
ON e1.mgr = e2.empno;
#29. 이름,부서명을 조회하시오.단, 사원테이블에 부서번호가 40에 속한 사원이 없지만 부서번호 40인 부서명도 출력되도록 하시오. (hint : outer join)
SELECT E.ENAME,D.DNAME
FROM DEPT D LEFT JOIN EMP E ON D.DEPTNO = E.DEPTNO;

#30. 부서번호가 30번인 사원들의 이름, 직급, 부서번호, 부서위치를 조회하시오. (hint : outer join)
SELECT e.ename, e.job, e.deptno, d.loc
FROM emp e
INNER JOIN dept d
ON e.deptno = d.deptno
WHERE e.deptno = 30;

#31. DALLAS에서 근무하는 사원의 이름,직급,부서번호,부서명을 조회하시오.
SELECT e.ename, e.job, e.deptno, d.loc
FROM emp e
INNER JOIN dept d
ON e.deptno = d.deptno
WHERE d.loc = 'DALLAS';

#32. 이름에 A 가 들어가는 사원의 이름,부서명을 조회하시오.
SELECT e.ename, d.dname
FROM emp e
INNER JOIN dept d
ON e.deptno = d.deptno
WHERE e.ename LIKE '%A%';

#33. 이름, 직급, 월급여, 월급여등급을 조회하시오.
SELECT e.ename, e.job, e.sal, s.grade
FROM emp e
INNER JOIN salgrade s
ON e.sal BETWEEN s.losal AND s.hisal;

#34. ALLEN과 같은 부서에 근무하는 사원의 이름, 부서번호를 조회하시오.
SELECT a.ename, a.deptno
FROM emp e
INNER JOIN emp a 
ON e.deptno = a.deptno
WHERE e.empno <> a.empno
AND e.ename = 'ALLEN'
ORDER BY a.ename;

#서브 쿼리는 SELECT 문 안에서 ()로 둘러싸인 SELECT 문을 말하며 쿼리문의 결과를 메인 쿼리로 전달하기 위해 사용된다.
#사원명 'JONES'가 속한 부서명을 조회하시오.
#부서번호를 알아내기 위한 쿼리가 서브 쿼리로 사용되고, 이 서브쿼리는 단 하나의 결과값을 얻기 때문에 단일 행 서브 쿼리라 한다.

#35. 10번 부서에서 근무하는 사원의 이름과 10번 부서의 부서명을 조회하시오. (hint : sub query)
SELECT e.ename, d.dname
FROM emp e,(
SELECT deptno, dname
FROM dept
WHERE deptno = 10
) d
WHERE E.DEPTNO = D.DEPTNO;

#36. 평균 월급여보다 더 많은 월급여를 받은 사원의 사원번호,이름,월급여 조회하시오. (hint : sub query)
SELECT empno, ename, sal
FROM emp e
WHERE e.sal > (SELECT AVG(sal)FROM emp);

#37. 부서번호가 10인 사원중에서 최대급여를 받는 사원과 동일한 급여를 받는 사원의 사원번호, 이름을 조회하시오. (hint : sub query)
SELECT empno, ename, sal
FROM emp e
WHERE e.deptno = 10
AND e.sal = (SELECT MAX(sal) FROM emp);
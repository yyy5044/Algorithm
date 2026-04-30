-- 조회: 
-- 1. 2022년도 한해 평가 점수가 가장 높은 사원 정보
-- 2. 점수, 사번, 성명, 직책, 이메일
-- 조건:
-- 1. 2022년도의 평가 점수는 상,하반기 점수의 합을 의미 
-- 2. 평가 점수를 나타내는 컬럼의 이름은 SCORE X

select sum(g.SCORE) as SCORE, e.EMP_NO, e.EMP_NAME, e.POSITION, e.EMAIL
from HR_EMPLOYEES as e
join HR_GRADE as g using(EMP_NO)
where YEAR = 2022
group by e.EMP_NO, e.EMP_NAME, e.POSITION, e.EMAIL
having sum(g.SCORE) = (
                    select max(year_total)
                    from(
                        select sum(SCORE) as year_total
                        from HR_GRADE
                        where YEAR = 2022
                        group by EMP_NO
                    ) sub
                );



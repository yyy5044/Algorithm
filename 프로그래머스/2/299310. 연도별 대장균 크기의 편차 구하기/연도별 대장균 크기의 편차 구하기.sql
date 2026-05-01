-- 조회: 
-- 1. 분화된 연도(YEAR), 
-- 2. 분화된 연도별 대장균 크기의 편차(YEAR_DEV)
-- 3. 대장균 개체의 ID(ID)
-- 조건:
-- 1. 결과는 연도에 대해 오름차순으로 정렬하고,
-- 2. 대장균 크기의 편차에 대해 오름차순으로 정렬

select sub.YEAR, (sub.MAX_SIZE - SIZE_OF_COLONY) as YEAR_DEV, ID
from (
        select year(DIFFERENTIATION_DATE) as YEAR, max(SIZE_OF_COLONY) as MAX_SIZE
        from ECOLI_DATA
        group by year(DIFFERENTIATION_DATE)
    ) sub
join ECOLI_DATA on sub.YEAR = year(DIFFERENTIATION_DATE)
order by sub.YEAR asc, YEAR_DEV asc;
-- 조회: 각 분기(QUARTER)별 분화된 대장균의 개체의 총 수(ECOLI_COUNT)를 출력
-- 조건:
-- 1. 이때 각 분기에는 'Q' 를 붙이고 x
-- 2. 분기에 대해 오름차순으로 정렬

select  concat(quarter(DIFFERENTIATION_DATE), 'Q') as QUARTER, 
        count(ID) as ECOLI_COUNT
from ECOLI_DATA
group by quarter(DIFFERENTIATION_DATE)
order by quarter(DIFFERENTIATION_DATE) asc;
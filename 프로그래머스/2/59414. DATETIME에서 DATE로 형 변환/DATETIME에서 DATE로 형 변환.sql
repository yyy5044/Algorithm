-- ANIMAL_INS: 동물 보호소에 들어온 동물의 정보
select ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') as `날짜`
from ANIMAL_INS
order by ANIMAL_ID;
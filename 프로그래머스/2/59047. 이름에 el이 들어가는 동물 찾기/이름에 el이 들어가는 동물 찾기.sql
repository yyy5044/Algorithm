-- ANIMAL_INS :  동물 보호소에 들어온 동물의 정보
select ANIMAL_ID, NAME
from ANIMAL_INS
where NAME like '%el%' and ANIMAL_TYPE = 'Dog'
order by NAME, ANIMAL_ID;
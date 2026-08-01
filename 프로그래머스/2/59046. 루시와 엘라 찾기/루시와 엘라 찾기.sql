-- ANIMAL_INS: 동물 보호소에 들어온 동물의 정보
select ANIMAL_ID, NAME, SEX_UPON_INTAKE
from ANIMAL_INS
where NAME in ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
order by ANIMAL_ID;
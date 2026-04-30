-- 조건: 동물 중 이름이 Lucy, Ella, Pickle, Rogan, Sabrina, Mitty인
-- 조회: 아이디와 이름, 성별 및 중성화 여부
select ANIMAL_ID, NAME, SEX_UPON_INTAKE
from ANIMAL_INS
where name in('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty');
-- ANIMAL_INS: 동물 보호소에 들어온 동물의 정보를 담은 테이블
-- ANIMAL_OUTS: 동물 보호소에서 입양 보낸 동물의 정보
select I.NAME, I.DATETIME
from ANIMAL_INS as I
left join ANIMAL_OUTS as O
on (I.ANIMAL_ID = O.ANIMAL_ID)
where O.DATETIME is null
order by I.DATETIME
limit 3;
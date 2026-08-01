-- ANIMAL_INS: 동물 보호소에 들어온 동물의 정보
-- ANIMAL_OUTS: 동물 보호소에서 입양 보낸 동물의 정보
select I.ANIMAL_ID, I.NAME
from ANIMAL_INS as I
join ANIMAL_OUTS as O
using (ANIMAL_ID)
where I.DATETIME > O.DATETIME
order by I.DATETIME asc;

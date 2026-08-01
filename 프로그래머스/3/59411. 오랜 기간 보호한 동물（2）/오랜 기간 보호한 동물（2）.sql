-- ANIMAL_INS : 동물 보호소에 들어온 동물의 정보
-- ANIMAL_OUTS : 동물 보호소에서 입양 보낸 동물의 정보
select I.ANIMAL_ID, I.NAME
from ANIMAL_INS as I
left join ANIMAL_OUTS as O
using (ANIMAL_ID)
where ANIMAL_ID is not null
order by DATEDIFF(O.DATETIME, I.DATETIME) desc
limit 2;



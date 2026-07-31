-- 12:37
-- ANIMAL_INS: 동물 보호소에 들어온 동물의 정보
-- ANIMAL_OUTS: 동물 보호소에서 입양 보낸 동물의 정보
select o.animal_id, o.name
from animal_outs as o
left join animal_ins as i
using (animal_id)
where i.animal_type is null
order by o.animal_id;
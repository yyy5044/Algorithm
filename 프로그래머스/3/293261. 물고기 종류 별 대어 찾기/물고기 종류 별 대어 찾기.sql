-- FISH_INFO: 잡은 물고기들의 정보
-- FISH_NAME_INFO: 물고기의 이름에 대한 정보
select ID, FISH_NAME, LENGTH
from FISH_INFO F
join FISH_NAME_INFO I
using (FISH_TYPE)
where (FISH_TYPE, LENGTH) in (
                select FISH_TYPE, MAX(LENGTH)
                from FISH_INFO
                group by FISH_TYPE
                );
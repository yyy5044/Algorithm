-- FISH_INFO: 잡은 물고기들의 정보
select COUNT(*) as FISH_COUNT, MAX(LENGTH) as MAX_LENGTH, FISH_TYPE
from (
    select ID, FISH_TYPE, IFNULL(LENGTH, 10) as LENGTH, TIME
    from FISH_INFO
) as sub
group by FISH_TYPE
having AVG(LENGTH) >= 33
order by FISH_TYPE;


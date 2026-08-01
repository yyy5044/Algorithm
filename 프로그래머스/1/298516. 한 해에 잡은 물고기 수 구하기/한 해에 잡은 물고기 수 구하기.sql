-- FISH_INFO: 잡은 물고기들의 정보
select COUNT(*) AS FISH_COUNT
from FISH_INFO
where TIME >= '2021-01-01' AND TIME < '2022-01-01';


select  count(*) as FISH_COUNT, FISH_NAME as FISH_NAME
from FISH_INFO
join FISH_NAME_INFO
using(FISH_TYPE)
group by FISH_TYPE
order by FISH_COUNT desc;

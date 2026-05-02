select count(*) as FISH_COUNT
from FISH_INFO i
join FISH_NAME_INFO n using(FISH_TYPE)
where n.FISH_NAME in('BASS', 'SNAPPER');
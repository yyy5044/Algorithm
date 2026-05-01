-- 조회: 잡은 BASS와 SNAPPER의 수를 출력
-- 조건: 컬럼명은 'FISH_COUNT`로

select count(FISH_TYPE) as FISH_COUNT
from FISH_INFO
join FISH_NAME_INFO using(FISH_TYPE)
where FISH_NAME IN ('BASS', 'SNAPPER');
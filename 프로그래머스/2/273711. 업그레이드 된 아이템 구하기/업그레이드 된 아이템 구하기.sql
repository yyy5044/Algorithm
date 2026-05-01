-- 조회:
-- 1. 아이템의 희귀도가 'RARE'인 아이템들의 모든 다음 업그레이드 아이템
-- 2. 아이템 ID(ITEM_ID), 아이템 명(ITEM_NAME), 아이템의 희귀도(RARITY)를 출력
-- 조건: 아이템 ID를 기준으로 내림차순 정렬

select  t.ITEM_ID,
        (select ITEM_NAME from ITEM_INFO where ITEM_ID=t.ITEM_ID) as ITEM_NAME,
        (select RARITY from ITEM_INFO where ITEM_ID=t.ITEM_ID) as RARITY
from ITEM_INFO as i
join ITEM_TREE as t
on i.ITEM_ID = t.PARENT_ITEM_ID
where i.RARITY = 'RARE'
order by t.ITEM_ID desc;
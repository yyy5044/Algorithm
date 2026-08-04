-- 코드를 입력하세요
select WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, ifnull(FREEZER_YN, 'N') as FREEZER_YN
from FOOD_WAREHOUSE
where ADDRESS like '경기도%'
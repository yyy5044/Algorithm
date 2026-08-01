-- 코드를 입력하세요
select SUBSTR(PRODUCT_CODE, 1, 2) AS CATEGORY, COUNT(*) AS PRODUCTS
from PRODUCT
group by CATEGORY
order by CATEGORY asc;
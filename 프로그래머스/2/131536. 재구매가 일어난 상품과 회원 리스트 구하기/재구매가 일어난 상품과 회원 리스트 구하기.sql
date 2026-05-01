-- 조회: 
-- 1. 동일한 회원이 동일한 상품을 재구매한 데이터 x
-- 2. 재구매한 회원 ID와 재구매한 상품 ID를 출력 x
-- 조건:
-- 1. 회원 ID를 기준으로 오름차순 정렬해주시고 x
-- 2. 회원 ID가 같다면 상품 ID를 기준으로 내림차순 정렬 x
select USER_ID, PRODUCT_ID
from ONLINE_SALE
group by USER_ID, PRODUCT_ID
having count(*) >= 2
order by USER_ID asc, PRODUCT_ID desc;

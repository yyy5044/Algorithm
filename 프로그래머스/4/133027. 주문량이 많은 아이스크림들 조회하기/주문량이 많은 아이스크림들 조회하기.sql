-- 코드를 입력하세요
-- FIRST_HALF: 상반기 주문 정보
-- JULY: 7월 주문 정보
select FLAVOR
from FIRST_HALF AS F
join JULY AS J
using(FLAVOR)
group by FLAVOR
order by SUM(J.TOTAL_ORDER + F.TOTAL_ORDER) desc
limit 3;

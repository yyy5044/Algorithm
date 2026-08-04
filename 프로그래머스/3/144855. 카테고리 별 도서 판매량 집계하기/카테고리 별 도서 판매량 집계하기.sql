-- BOOK: 서점에서 판매중인 도서들의 도서 정보
-- BOOK_SALES: 판매 정보
select B.CATEGORY, SUM(S.SALES) as TOTAL_SALES
from BOOK_SALES S
join BOOK B using (BOOK_ID)
where YEAR(S.SALES_DATE) = '2022' and MONTH(S.SALES_DATE) = 1
group by B.CATEGORY
order by B.CATEGORY;

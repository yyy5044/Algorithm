select BOOK_ID, AUTHOR_NAME, PUBLISHED_DATE
from BOOK as b
join AUTHOR as a
on b.AUTHOR_ID = a.AUTHOR_ID
where CATEGORY = '경제'
order by PUBLISHED_DATE;

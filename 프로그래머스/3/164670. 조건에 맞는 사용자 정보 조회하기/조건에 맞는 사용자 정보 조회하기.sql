-- USED_GOODS_BOARD: 중고 거래 게시판 정보
-- USED_GOODS_USER: 중고 거래 게시판 첨부파일 정보
select U.USER_ID, U.NICKNAME, 
    CONCAT(U.CITY, ' ', U.STREET_ADDRESS1, ' ', U.STREET_ADDRESS2 ) AS '전체주소', CONCAT(SUBSTR(U.TLNO, 1, 3), '-', SUBSTR(U.TLNO, 4, 4), '-', SUBSTR(U.TLNO, 8, 4)) AS '전화번호'
from USED_GOODS_USER as U
join USED_GOODS_BOARD as B
on (U.USER_ID = B.WRITER_ID)
group by U.USER_ID
having COUNT(U.USER_ID) >= 3
order by U.USER_ID desc;


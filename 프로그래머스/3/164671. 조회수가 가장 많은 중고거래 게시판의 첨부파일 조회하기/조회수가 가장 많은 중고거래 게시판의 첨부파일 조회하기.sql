-- USED_GOODS_BOARD: 중고거래 게시판 정보
-- USED_GOODS_FILE: 중고거래 게시판 첨부파일 정보
select CONCAT('/home/grep/src/', BOARD_ID, '/', FILE_ID, FILE_NAME, FILE_EXT) as `FILE_PATH`
from USED_GOODS_FILE as F
join USED_GOODS_BOARD as B
using (BOARD_ID)
where B.views = (   select MAX(VIEWS)
                    from USED_GOODS_BOARD)
order by FILE_ID desc;
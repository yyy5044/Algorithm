-- root 아이템이면 parent 아이템 id가 null임을 이용 -> 문제에서 알려주기도 하네
select i.ITEM_ID, i.ITEM_NAME
from ITEM_INFO as i
join ITEM_TREE as t
using(ITEM_ID)
where PARENT_ITEM_ID is null
order by ITEM_ID;
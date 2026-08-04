select ITEM_ID, ITEM_NAME
from ITEM_INFO
join ITEM_TREE
using (ITEM_ID)
where PARENT_ITEM_ID IS NULL
order by ITEM_ID asc;
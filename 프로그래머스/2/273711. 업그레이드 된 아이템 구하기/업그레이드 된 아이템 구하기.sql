select child.ITEM_ID, child.ITEM_NAME, child.RARITY
from ITEM_TREE t
join ITEM_INFO parent on (t.PARENT_ITEM_ID = parent.ITEM_ID)
join ITEM_INFO child on (t.ITEM_ID = child.ITEM_ID)
where parent.RARITY = 'RARE'
order by child.ITEM_ID desc;

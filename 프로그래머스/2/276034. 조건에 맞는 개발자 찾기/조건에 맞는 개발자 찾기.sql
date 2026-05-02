select distinct d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
from DEVELOPERS d
join SKILLCODES s on (s.CODE & d.SKILL_CODE) > 0
where s.NAME in ('Python', 'C#')
order by d.ID;
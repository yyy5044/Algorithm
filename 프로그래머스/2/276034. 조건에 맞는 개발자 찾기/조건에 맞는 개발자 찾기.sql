-- 개발자의 ID, 이메일, 이름, 성

select d.ID, d.EMAIL, FIRST_NAME, LAST_NAME
from DEVELOPERS d
where exists(
                select CODE
                from SKILLCODES s
                where name IN('Python', 'C#') AND d.SKILL_CODE & s.CODE > 0
            )
order by d.ID;


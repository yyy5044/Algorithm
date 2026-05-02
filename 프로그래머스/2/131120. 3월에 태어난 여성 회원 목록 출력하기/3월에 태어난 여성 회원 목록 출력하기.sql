select MEMBER_ID, MEMBER_NAME, GENDER, DATE_OF_BIRTH
from MEMBER_PROFILE
where month(DATE_OF_BIRTH) = 3 and GENDER = 'W' and TLNO is not null
order by MEMBER_ID;
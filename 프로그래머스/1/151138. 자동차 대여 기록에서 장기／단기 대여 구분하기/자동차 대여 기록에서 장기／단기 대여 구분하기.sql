select  HISTORY_ID, CAR_ID, START_DATE, END_DATE,
        case
            when datediff(END_DATE, START_DATE) >= 29 then '장기 대여'
            else '단기 대여'
        end as RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where START_DATE like '2022-09-%'
order by HISTORY_ID desc;
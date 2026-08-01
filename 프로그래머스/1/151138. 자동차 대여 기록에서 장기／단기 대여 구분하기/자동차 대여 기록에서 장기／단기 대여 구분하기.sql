-- CAR_RENTAL_COMPANY_RENTAL_HISTORY: 자동차 대여 회사의 자동차 대여 기록 정보
select HISTORY_ID, CAR_ID, START_DATE, END_DATE,
    case 
        when DATEDIFF(END_DATE, START_DATE) + 1 >= 30 then '장기 대여'
        else '단기 대여'
    end as RENT_TYPE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where START_DATE >= '2022-09-01' and START_DATE < '2022-10-01'
order by HISTORY_ID desc;

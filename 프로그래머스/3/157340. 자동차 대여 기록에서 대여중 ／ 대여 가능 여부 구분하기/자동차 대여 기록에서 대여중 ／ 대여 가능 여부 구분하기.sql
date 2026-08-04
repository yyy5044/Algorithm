select H.CAR_ID,
    case
        when SUB.CAR_ID is null then '대여 가능'
        else '대여중'
    end as AVAILABILITY
from (
    select distinct CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
) H
left join (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE <= '2022-10-16' and END_DATE >= '2022-10-16'
    group by CAR_ID
) SUB
on (H.CAR_ID = SUB.CAR_ID)
order by CAR_ID desc;

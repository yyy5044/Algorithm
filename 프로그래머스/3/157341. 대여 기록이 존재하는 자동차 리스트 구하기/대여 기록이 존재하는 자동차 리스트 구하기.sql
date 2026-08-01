-- CAR_RENTAL_COMPANY_CAR: 대여 중인 자동차들의 정보
-- CAR_RENTAL_COMPANY_RENTAL_HISTORY: 자동차 대여 기록 정보
select distinct H.CAR_ID
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as H
join CAR_RENTAL_COMPANY_CAR as C
using (CAR_ID)
where CAR_TYPE = '세단'
and MONTH(START_DATE) = 10
order by H.CAR_ID desc;

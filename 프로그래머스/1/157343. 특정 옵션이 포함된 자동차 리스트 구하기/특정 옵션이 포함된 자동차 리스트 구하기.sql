-- CAR_RENTAL_COMPANY_CAR: 대여중인 자동차들의 정보
select *
from CAR_RENTAL_COMPANY_CAR
where OPTIONS like '%네비게이션%'
order by CAR_ID desc;
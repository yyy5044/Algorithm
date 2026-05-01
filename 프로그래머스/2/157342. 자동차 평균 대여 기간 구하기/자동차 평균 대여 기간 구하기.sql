-- 조회: 평균 대여 기간이 7일 이상인 자동차들 x 의 자동차 ID와 평균 대여 기간(컬럼명: AVERAGE_DURATION) 리스트를 출력
-- 조건: 
-- 1. 평균 대여 기간은 소수점 두번째 자리에서 반올림 x
-- 2. 결과는 평균 대여 기간을 기준으로 내림차순 정렬 x
-- 3. 평균 대여 기간이 같으면 자동차 ID를 기준으로 내림차순 정렬 x

select CAR_ID, round(avg(datediff)+1, 1) as AVERAGE_DURATION
from (
        select CAR_ID, datediff(END_DATE, START_DATE) as datediff
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    ) sub
group by CAR_ID
having avg(datediff)+1 >= 7
order by AVERAGE_DURATION desc, CAR_ID desc;

-- 조회: 노선 x, 총 누계 거리 x, 평균 역 사이 거리 x를 노선별로 조회
-- 조건:
-- 1. 컬럼명: 총 누계 거리(TOTAL_DISTANCE), 평균 역 사이 거리(AVERAGE_DISTANCE) x
-- 2. 총 누계거리는 소수 둘째자리에서 반올림 x
-- 3. 평균 역 사이 거리는 소수 셋째 자리에서 반올림 x
-- 4. 단위(km)를 함께 출력 x
-- 5. 결과는 총 누계 거리를 기준으로 내림차순 정렬 x
select  ROUTE,
        concat(round(sum(D_BETWEEN_DIST), 1), 'km') as TOTAL_DISTANCE,
        concat(round(avg(D_BETWEEN_DIST), 2), 'km') as AVERAGE_DISTANCE
from SUBWAY_DISTANCE
group by ROUTE
order by sum(D_BETWEEN_DIST) desc;


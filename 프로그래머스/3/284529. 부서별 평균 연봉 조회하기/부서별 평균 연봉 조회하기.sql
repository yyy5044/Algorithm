-- HR_DEPARTMENT: 회사의 부서 정보
-- HR_EMPLOYEES: 회사의 사원 정보
select D.DEPT_ID, D.DEPT_NAME_EN, ROUND(AVG(E.SAL), 0) as AVG_SAL 
from HR_DEPARTMENT D join HR_EMPLOYEES E
on (D.DEPT_ID = E.DEPT_ID)
group by D.DEPT_ID
order by AVG_SAL desc;


select sum(SCORE) as SCORE, EMP_NO, EMP_NAME, POSITION, EMAIL
from HR_EMPLOYEES
join HR_GRADE using(EMP_NO)
group by EMP_NO
having sum(SCORE) = (
                        select max(year_total)
                        from (
                                select sum(SCORE) as year_total
                                from HR_GRADE
                                where YEAR = 2022
                                group by EMP_NO
                            ) sub
                    );


    
    
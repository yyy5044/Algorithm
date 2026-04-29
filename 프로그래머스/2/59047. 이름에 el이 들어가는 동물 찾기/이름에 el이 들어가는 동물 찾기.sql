select ANIMAL_ID, NAME
from ANIMAL_INS
where name like '%el%' and animal_type = 'Dog'
order by name;
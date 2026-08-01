-- ANIMAL_INS : 동물 보호소에 들어온 동물의 정보
select ANIMAL_ID, NAME, 
    case 
        when (SEX_UPON_INTAKE like 'Neutered%') or (SEX_UPON_INTAKE like 'Spayed%') then 'O'
        else 'X'
    end as `중성화`
from ANIMAL_INS
order by ANIMAL_ID;

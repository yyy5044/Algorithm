select count(*) as COUNT
from ECOLI_DATA
where ((GENOTYPE & 4) != 0 or (GENOTYPE & 1) != 0) and (GENOTYPE & 2) = 0;
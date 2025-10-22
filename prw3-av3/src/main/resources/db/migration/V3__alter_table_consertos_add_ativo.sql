alter table consertos add ativo tinyint;

-- tds os registros existentes viram ativos no momento da migracao
update consertos set ativo = 1;

-- eh opcional mas pelo q vi garante que n fique nulo daqui pra frente
alter table consertos alter column ativo set not null;
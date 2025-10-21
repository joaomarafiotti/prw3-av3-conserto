alter table consertos add ativo tinyint;

-- Todos os registros existentes passam a 'ativos' no momento da migração
update consertos set ativo = 1;

-- (opcional, recomendado) garantir que não fique nulo daqui pra frente
alter table consertos alter column ativo set not null;
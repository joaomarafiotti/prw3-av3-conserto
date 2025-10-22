create table consertos (
  id bigint not null auto_increment,
  data_entrada varchar(10) not null,
  data_saida   varchar(10),

  -- mecanico (embedded)
  mecanico_nome varchar(100) not null,
  mecanico_anos_experiencia int not null,

  -- veculo (embedded)
  veiculo_marca  varchar(60) not null,
  veiculo_modelo varchar(60) not null,
  veiculo_ano    varchar(4)  not null,

  primary key (id)
);
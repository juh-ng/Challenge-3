create table melhorias(
    id bigint not null auto_increment,
    proposta_melhoria varchar(255) not null,
    qt_votos bigint default 0,
    voto varchar (50)
    primary key (id)
);
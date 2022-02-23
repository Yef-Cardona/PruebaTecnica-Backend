create table rol (
id_rol int(10) not null auto_increment,
nombre varchar(50) not null,
primary key (id_rol)
);

create table usuario (
id_usuario int(10) not null auto_increment,
nombre varchar(50) not null,
activo char not null,
id_rol int(10) not null,
primary key (id_usuario),
foreign key (id_rol) references rol(id_rol)
);

##############################Insert
insert into rol(nombre) values ('ADMINISTRADOR');
insert into rol(nombre) values ('AUDITOR');
insert into rol(nombre) values ('AUXILIAR');
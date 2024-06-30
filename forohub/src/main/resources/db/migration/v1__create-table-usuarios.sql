create table usuarios (

    id bigint not null auto_increment,
    contrasena varchar(100) not null,
    correo_electronico varchar(100) not null,
    nombre varchar(100) not null,

    primary key (id)

);

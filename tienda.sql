CREATE SEQUENCE public.sequence_productos
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999999999999
    CACHE 20;

create table productos(
	idproducto int not null DEFAULT nextval('sequence_productos'),
	marca varchar(50) not null,
	producto varchar(50) not null,
	precio varchar (20),
	descripcion varchar(100),
	estatus varchar (20)
);

ALTER SEQUENCE sequence_productos
OWNED BY productos.idproducto;





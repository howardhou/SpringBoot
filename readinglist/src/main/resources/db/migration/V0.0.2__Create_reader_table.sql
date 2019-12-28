create table tb_reader (
id serial primary key,
username varchar(25) unique not null,
password varchar(25) not null,
fullname varchar(50) not null
);

-- create sequence hibernate_sequence;

insert into tb_reader (username, password, fullname)
values ('houdongdong', '123456', 'Howard Hou');
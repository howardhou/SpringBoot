create table tb_book (
id serial primary key,
author varchar(50) not null,
description varchar(1000) not null,
isbn varchar(30) not null,
title varchar(250) not null,
reader_username varchar(25) not null
-- 去掉外键约束
-- foreign key (reader_username) references tb_reader(username)
);

insert into tb_book (title, isbn, author, description, reader_username)
values ('Docker 实战', '978-2-1234-1212-45', 'Jeff Nickoloff', 'Docker实践会教会读者如何创建、部署、管理Docker容器托管的应用程序', 'houdongdong');
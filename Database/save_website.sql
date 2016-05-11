create database SAVE_WEBSITE DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create table URL
(
	Id int not null auto_increment primary key,
    LinkUrl varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    TimeSave datetime
);

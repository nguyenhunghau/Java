create database FOLLOW_WEBSITE DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create table User
(
	Id int not null auto_increment primary key,
    Username varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci ,
	Passwords varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci
);

create table Url
(
	Id int not null auto_increment primary key,
    IdUser int,
    Frequent int,
    LinkUrl varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    TimeSave date,
    Pc varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    Tablet varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    Mobile varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
    Html varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci,
	constraint FK_Url_User foreign key (IdUser) references User(Id)
);

insert into User (Id, Username,Passwords)  values (1, 'admin','e10adc3949ba59abbe56e057f20f883e');

Create database STUDENT_MANAGER DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create TABLE Student (

	Id varchar(6) not null primary key,
	NameStudent  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci ,
	Birthday datetime,
	Gender varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci ,	
	Address varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci ,
	ReceiveDay date,
    ClassId int
);

create table Class 
(
	Id int not null auto_increment primary key,
	NameClass varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci ,
	CourseId int
);

create table Course 
(
	Id int not null auto_increment primary key,
	YearBegin nvarchar(4) ,
	YearEnd varchar(4)
);

create table Semester
(
	Id int not null auto_increment primary key,
    NameSemester varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  ,
	CourseId int,
	MonthBegin int,
	MonthEnd int
);

create table Subject 
(
	Id int not null auto_increment primary key,
	NameSubject varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci 
);

create table Score
(
	Id int not null auto_increment primary key,
	StudentId varchar(6) not null,
	SemesterId int not null, 
	SubjectId int not null,
	Score1 float,
    Score2 float,
    Score3 float
);

create table User
(
	Id int not null auto_increment primary key,
	TypeId int not null,
    Username varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci ,
	Passwords varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci 
);

create table TypeUser
(
	Id int not null auto_increment primary key,
	NameType varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci 
);

/* foreign key */
alter table Class add constraint FK_Class_Course foreign key (CourseId) references Course(Id);
alter table Semester add constraint FK_Semester_Course foreign key (CourseId) references Course(Id);
alter table Score add constraint FK_Score_Student foreign key (StudentId) references Student(Id);
alter table Score add constraint FK_Score_Semester foreign key (SemesterId) references Semester(Id);
alter table Score add constraint FK_Score_Subject foreign key (SubjectId) references Subject(Id);
alter table User add constraint FK_User_TypeUser foreign key (TypeId) references TypeUser(Id);

/* insert data */

insert into Course Values(1,2016,1019);
insert into Course Values(2,2015,2018);
insert into Course Values(3,2014,2017);
insert into Class(NameClass,CourseId) Values('10A1',1);
insert into Class(NameClass,CourseId) Values('10A2',1);
insert into Class(NameClass,CourseId) Values('10A3',1);
insert into Class(NameClass,CourseId) Values('10A4',1);
insert into Class(NameClass,CourseId) Values('10A5',1);
insert into Class(NameClass,CourseId) Values('10A6',1);

insert into Class(NameClass,CourseId) Values('10A1',2);
insert into Class(NameClass,CourseId) Values('10A2',2);
insert into Class(NameClass,CourseId) Values('10A3',2);
insert into Class(NameClass,CourseId) Values('10A1',3);
insert into Class(NameClass,CourseId) Values('10A2',3);
insert into Class(NameClass,CourseId) Values('10A3',3);

insert into Student Values('160001','Nguyen Van A','2013-07-17 18:33:55','Male','Ho Chi Minh','2013-07-17',1);

insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S1',1,9,1);
insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S2',1,2,6);
insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S1',2,9,1);
insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S2',2,2,6);
insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S1',3,9,1);
insert into Semester(NameSemester,CourseId,MonthBegin,MonthEnd) Values ('S2',3,2,6);

insert into Subject(NameSubject) values ('Science');
insert into Subject(NameSubject) values ('Literature');
insert into Subject(NameSubject) values ('Math');
insert into Subject(NameSubject) values ('Physic');

insert into Score (StudentId,SemesterId,SubjectId,Score1,Score2,Score3) Values ('160001',1,1,6,7,8);
insert into Score (StudentId,SemesterId,SubjectId,Score1,Score2,Score3) Values ('160001',1,2,6,7,8);
insert into Score (StudentId,SemesterId,SubjectId,Score1,Score2,Score3) Values ('160001',1,3,6,7,8);
insert into Score (StudentId,SemesterId,SubjectId,Score1,Score2,Score3) Values ('160001',1,4,6,7,8);

insert into TypeUser (NameType) values ('Teacher');
insert into User (TypeId,Username,Passwords) values (1,'teacher','e10adc3949ba59abbe56e057f20f883e')
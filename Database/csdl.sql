Create database STUDENT_MANAGER;

create TABLE Student (

	ID varchar(6) not null primary key,
	NameStudent  nvarchar(50),
	Birthday datetime,
	Gender nvarchar(10) check (Gender in (Male, Female)),	
	Address nvarchar(20),
	ReceiveDay date
);

create table Class 
(
	ID int not null auto_increment primary key,
	NameClass nvarchar(20) ,
	CourseID int
);

create table Course 
(
	ID int not null auto_increment primary key,
	YearBegin nvarchar(4) ,
	YearEnd varchar(4)
);

create table Semester
(
	ID int not null auto_increment primary key,
    NameSemester varchar(20) ,
	CourseID int,
	MonthBegin int,
	MonthEnd int
);

create table Subject 
(
	ID int not null auto_increment primary key,
	NameSubject nvarchar(50)
);

create table Score
(
	ID int not null auto_increment primary key,
	StudentID varchar(6) not null,
	SemesterID int not null, 
	SubjectID int not null,
	Score1 float,
    Score2 float,
    Score3 float
);

create table Rule
(
	ID int not null auto_increment primary key,
	nMaxStudent int,
	OldMin int, 
	OldMax int,
	nMaxSubject int, 
	Benchmark float
);


create table User
(
	ID int not null auto_increment primary key,
	TypeID int not null,
	Passwords varchar(100)
);

create table TypeUser
(
	ID int not null auto_increment primary key,
	NameType nvarchar(20)
);

/* foreign key */
alter table Class add constraint FK_Class_Course foreign key (CourseID) references Course(ID);
alter table Semester add constraint FK_Semester_Course foreign key (CourseID) references Course(ID);
alter table Score add constraint FK_Score_Student foreign key (StudentID) references Student(ID);
alter table Score add constraint FK_Score_Semester foreign key (SemesterID) references Semester(ID);
alter table Score add constraint FK_Score_Subject foreign key (SubjectID) references Subject(ID);
alter table User add constraint FK_User_TypeUser foreign key (TypeID) references TypeUser(ID);

/* insert data */
insert into Student Values(120002,'Nguyen Van A','2013-07-17 18:33:55','Mae','Ho Chi Minh','2013-07-17');
insert into Course Values(1,2016,1019);
insert into Course Values(2,2015,2018);
insert into Course Values(3,2014,2017);
insert into Class(NameClass,CourseID) Values('10A1',1);
insert into Class(NameClass,CourseID) Values('10A2',1);
insert into Class(NameClass,CourseID) Values('10A3',1);
insert into Class(NameClass,CourseID) Values('10A4',1);
insert into Class(NameClass,CourseID) Values('10A5',1);
insert into Class(NameClass,CourseID) Values('10A6',1);

insert into Semester(NameSemester,CourseID,MonthBegin,MonthEnd) Values ('S1',1,9,1);
insert into Semester(NameSemester,CourseID,MonthBegin,MonthEnd) Values ('S2',1,2,6);

insert into Subject(NameSubject) values ('Science');
insert into Subject(NameSubject) values ('Literature');
insert into Subject(NameSubject) values ('Math');
insert into Subject(NameSubject) values ('Physic');

insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values ('120002',1,1,6,7,8);
insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values ('120002',1,2,6,7,8);
insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values ('120002',1,3,6,7,8);
insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values ('120002',1,4,6,7,8);
insert into Score (StudentID,SemesterID,SubjectID,Score1,Score2,Score3) Values ('160003',1,1,6.0,9.0,3.0)

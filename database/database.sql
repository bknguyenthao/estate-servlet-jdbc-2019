use estate;

create table role(
 id bigint not null primary key auto_increment,
 name varchar(255) not null,
 code varchar(255) not null,
 
 createddate timestamp null,
 modifieddate timestamp null,
 createdby varchar(255) null,
 modifiedby varchar(255) null
);

create table user(
id bigint not null primary key auto_increment,
username varchar(150) not null,
password varchar(150) not null,
fullname varchar (255) null,
status int not null,

 createddate timestamp null,
 modifieddate timestamp null,
 createdby varchar(255) null,
 modifiedby varchar(255) null
);

create table user_role(
id bigint not null primary key auto_increment,
roleid bigint not null,
userid bigint not null
);
alter table user_role add constraint fk_userrole_role foreign key (roleid) references role(id);
alter table user_role add constraint fk_userrole_user foreign key (userid) references user(id);

create table building (
id bigint not null primary key auto_increment,
name varchar(255) null,
numberofbasement int null,
buildingarea int null,
district varchar(100) null,
ward varchar(100) null,
street varchar(100) null,
structure varchar(100) null,
costrent int null,
costdescription text null,
servicecost varchar(255) null,
carcost varchar(255) null,
motorbikecost varchar(255) null,
overtimecost varchar(255) null,
electriccitycost varchar(255) null,
deposit varchar(255) null,
payment varchar(255) null,
timerent varchar(255) null,
timedecorator varchar(255) null,
managername varchar (255) null,
managerphone varchar(255) null,
buildingtype text null,

 createddate timestamp null,
 modifieddate timestamp null,
 createdby varchar(255) null,
 modifiedby varchar(255) null
);

create table district(
id bigint not null primary key auto_increment,
name varchar(255) not null,
code varchar (255) not null,

 createddate timestamp null,
 modifieddate timestamp null,
 createdby varchar(255) null,
 modifiedby varchar(255) null
);

create table assignmentbuilding(
id bigint not null primary key auto_increment,
buildingid bigint not null,
staffid bigint not null
);

alter table assignmentbuilding add constraint fk_assignment_user foreign key (staffid) references user(id);
alter table assignmentbuilding add constraint fk_assignment_building foreign key (buildingid) references building(id);


create table rentarea(
id bigint not null primary key auto_increment,
value varchar(255) not null,
buildingid bigint not null,	
 createddate timestamp null,
 modifieddate timestamp null,
 createdby varchar(255) null,
 modifiedby varchar(255) null
);
alter table rentarea add constraint fk_rentarea_building foreign key (buildingid) references building(id);


--------------------------------------������Ϣ��-------------------------01
drop table waccount cascade constraint purge;
create table waccount (
id number(9) constraint waccount_id_pk primary key,
recommender_id number(9) constraint waccount_recmid_fk 
references waccount(id),
login_name varchar2(30) constraint waccount_ln_uk unique 
not null,
login_passwd varchar2(8)  not null,
status char(1)  not null ,
constraint waccount_status_ck  check(status in (0,1,2)),
create_date date default sysdate,
pause_date date default null,
close_date date default null,
real_name varchar2(20) not null,
idcard_no char(18) constraint waccount_idcard_uk not null 
unique ,
birthdate date default null,
gender char(1) not null constraint waccount_gender_ck check(gender IN(0,1)),
occupation varchar2(50) default null,
telephone varchar2(15) not null,
email varchar2(50) default null,
mailaddress varchar2(50) default null,
zipcode char(6) default null,
qq varchar2(15) default null,
last_login_time date default null,
last_login_ip varchar2(15) default null
);

ALTER SESSION SET NLS_DATE_FORMAT = 'yyyy mm dd hh24:mi:ss';
INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
     REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1005,NULL,'taiji001','256528',1,'2008 03 15','zhangsanfeng','19430225','1','410381194302256528',13669351234);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1020,NULL,'kxhxd20','012115',1,'2012 02 20','weixiaobao','20001001','1','321022200010012115',13953410078);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1010,NULL,'xl18z60','190613',1,'2009 01 10','guojing','19690319','1','330682196903190613',13338924567);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1011,1010,'dgbf70','270429',1,'2009 03 01','huangrong','19710827','0','330902197108270429',13637811357);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1015,1005,'mjjzh64','041115',1,'2010 03 12','zhangwuji','19890604','1','610121198906041115',13572952468);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1018,1011,'jmdxj00','010322',1,'2011 01 01','guofurong','199601010322','0','350581200201010322',18617832562);

INSERT INTO waccount(ID,RECOMMENDER_ID,LOGIN_NAME,LOGIN_PASSWD,STATUS,CREATE_DATE,
REAL_NAME,BIRTHDATE,gender,IDCARD_NO,TELEPHONE)
VALUES(1019,1011,'ljxj90','310346',1,'2012 02 01','luwushuang','19930731','0','320211199307310346',13186454984);
COMMIT;

------------------------------�ʷ���Ϣ��----------------------------02--
drop table wcost cascade constraint purge;
create table wcost(
id number(4) constraint wcost_id_pk primary key not null,
name varchar2(50) not null,
base_duration number(11) default null,
base_cost number(7,2) default null,
unit_cost number(7,4) default null,
status char(1) not null,
constraint wcost_status_ck check(status in (0,1,2)),
descr varchar2(100) default null,
creatime date default sysdate,
startime date default null,
cost_type char(1) default null
);

INSERT INTO wcost VALUES (1,'5.9Ԫ�ײ�',20,5.9,0.4,0,'5.9Ԫ20Сʱ/�� ��������0.4Ԫ/ʱ',DEFAULT,NULL,1);
INSERT INTO wcost VALUES (2,'6.9Ԫ�ײ�',40,6.9,0.3,0,'6.9Ԫ40Сʱ/�� ��������0.3Ԫ/ʱ',DEFAULT,NULL,1);
INSERT INTO wcost VALUES (3,'8.5Ԫ�ײ�',100,8.5,0.2,0,'8.5Ԫ100Сʱ/�� ��������0.2Ԫ/ʱ',DEFAULT,NULL,2);
INSERT INTO wcost VALUES (4,'10.5Ԫ�ײ�',200,10.5,0.1,0,'10.5Ԫ200Сʱ/�� ��������0.1Ԫ/ʱ',DEFAULT,NULL,3);
INSERT INTO wcost VALUES (5,'��ʱ�շ�',null,null,0.5,0,'0.5Ԫ/ʱ ��ʹ�ò��շ�',DEFAULT,NULL,2);
INSERT INTO wcost VALUES (6,'����',null,20,null,0,'ÿ��20Ԫ ������ʹ��ʱ��',DEFAULT,NULL,3);
commit;

------------------------------------ҵ����Ϣ��-----------------------03
drop table wservice cascade constraint purge;
create table wservice (
id number(10) constraint wservice_id_pk primary key not null,
account_id number(9)  constraint  wservice_accountid_fk references waccount(id) not null ,
unix_host varchar2(15) not null ,
os_username varchar2(15) not null,
login_passwd varchar2(8) not null,
status char(1) not null , 
create_date date default sysdate,
pause_date date default null,
close_date date default null,
cost_id number(4) constraint wservice_costid_fk references wcost(id) ��
constraint wservice_unix_os_uk unique(unix_host,os_username)��
CONSTRAINT SERVICE_STATUS_CK CHECK ( STATUS IN (0,1,2) )
);

INSERT INTO wservice VALUES (2001,1010,'192.168.0.26','guojing','guo1234',0,DEFAULT,DEFAULT,DEFAULT,1);
INSERT INTO wservice VALUES (2002,1011,'192.168.0.26','huangr','huang234',0,DEFAULT,null,null,1);
INSERT INTO wservice VALUES (2003,1011,'192.168.0.20','huangr','huang234',0,DEFAULT,null,null,3);
INSERT INTO wservice VALUES (2004,1011,'192.168.0.23','huangr','huang234',0,DEFAULT,null,null,6);
INSERT INTO wservice VALUES (2005,1019,'192.168.0.26','luwsh','luwu2345',0,DEFAULT,null,null,4);
INSERT INTO wservice VALUES (2006,1019,'192.168.0.20','luwsh','luwu2345',0,DEFAULT,null,null,5);
INSERT INTO wservice VALUES (2007,1020,'192.168.0.20','weixb','wei12345',0,DEFAULT,null,null,6);
INSERT INTO wservice VALUES (2008,1010,'192.168.0.20','guojing','guo09876',0,DEFAULT,null,null,6);
COMMIT;

--------------------------------------ҵ���굥��Ϣ��--------------------04
drop table wservice_detail cascade constraint purge;
create table wservice_detail(
id number(11) primary key not null,
service_id number (10) constraint service_detail_id_pk
references wservice(id),
client_host varchar2(15) default null,
os_username varchar2(8) default null,
pid number(11) default null,
login_time date default null,
logout_time date default null,
duration number(20,9) default null,
cost number(20,6) default null
);

-------------------------------------unix��������Ϣ��------------------------05
drop table whost cascade constraint purge;
create table whost(
id varchar2(15) primary key ,
name varchar2(20) not null,
location varchar2(20)
);
INSERT INTO whost VALUES ('192.168.0.26','sunv210','beijing');
INSERT INTO whost VALUES('192.168.0.20','sun-server','beijing');
INSERT INTO whost VALUES ('192.168.0.23','sun280','beijing');
INSERT INTO whost VALUES ('192.168.0.200','ultra10','beijing');
COMMIT;
-------------------------------------ҵ����±��ݱ�---------------------------06
drop table wservice_update_bak cascade constraint purge;
create table wservice_update_bak(
id number(10) primary key not null,
service_id number(9) constraint wsvc_ud_bak_id_fk
references wservice(id) not null,
cost_id number(4) constraint wsvc_ud_bak_costid_fk
references wcost(id)
);
-----------------------------------����ֶ���Ϣ��-----------------------------07
drop table wage_segment cascade constraint purge;
create table  wage_segment(
id number (1) primary key,
name varchar2(20) not null,
lowage number(2),
hiage number(2)
);

INSERT INTO wage_segment VALUES (0,'�����淴��',11,14);
INSERT INTO wage_segment VALUES (1,'����ɳ���',15,17);
INSERT INTO wage_segment VALUES (2,'�����ഺ��',18,28);
INSERT INTO wage_segment VALUES (3,'���������',29,40);
INSERT INTO wage_segment VALUES (4,'����׳ʵ��',41,48);
INSERT INTO wage_segment VALUES (5,'�����Ƚ���',49,55);
INSERT INTO wage_segment VALUES (6,'�����Ƚ���',56,65);
INSERT INTO wage_segment VALUES (7,'���������',66,72);
COMMIT;
-----------------------------------ʱ����Ϣ��--------------------------------08
drop table month_duration cascade constraint purge;
create table month_duration(
service_id number(10),
month_id char(6),
service_detail_id number(11),
sofar_duration number(11) default null
);
------------------------------------�ʵ���Ϣ��-------------------------------09
drop table wbill cascade constraint purge;
create table wbill (
id number(11) primary key not null,
account_id number(9) constraint wbill_accountid_fk
references waccount(id) not null,
bill_month char(6) not null,
cost number(13,2) not null,
payment_mode char(1) default null check(payment_mode IN(1,1,2,3)),
pay_state char(1) default 0 check(pay_state IN(0,1))
);
------------------------------------�ʵ���Ŀ��------------------------------10
drop table wbill_item cascade constraint purge;
create table wbill_item (
item_id number(11) primary key,
bill_id number(11) constraint wbill_item_fk 
references wbill(id),
service_id number(10) constraint wbill_item_srvid 
references wservice(id),
cost number(13,2) default null
);
------------------------------------��ɫ��---------------------------------11
drop table wrole_info cascade constraint purge;
create table wrole_info(
id number(11) primary key not null,
name varchar2(20) not null
);
insert into wrole_info values(1,'����');
-----------------------------------��ɫ��ϢȨ�ޱ�----------------------------12
drop table wrole_privilege cascade constraint purge;
create table wrole_privilege(
role_id number(4)  not null,
privilege_id number(4) not null,
constraint wrole_privilege_union_uk primary key(role_id,privilege_id)
);
------------------------------------����Ա��--------------------------------13
drop table wadmin_info cascade constraint purge;
create table wadmin_info(
id number(4) primary key not null,
admin_code varchar2(30) unique not null,
password varchar2(50) not null,
name varchar2(20) not null,
telephone varchar2(15) default null,
email varchar2(50) default null,
enrolldate date not null
);
 insert into wadmin_info values(1,'wang','wang','wang','18765831631','1156721874@qq.com',sysdate);
 commit;
---------------------------------����Ա��ɫ��---------------------------------14
drop table wadmin_role cascade constraint purge;
create table wadmin_role(
admin_id number(4)  not null references 
wadmin_info(id),
role_id number(4)  not null references 
wrole_info(id),
constraint wadmin_roleunion_uk primary key(admin_id,role_id)
);
insert into wadmin_role values(1,1);
----------------------------����ʱ�?������ʵ���ű�--------------------------15
drop table wbill_code cascade constraint purge;
create table wbill_code(
item_id number(11),
account_id number(9),
bill_month char(6)
);

----------------------------��ʱ�洢bill_item_temp---------------------------16
drop table wbill_item_temp cascade constraints purge;
create table wbill_item_temp(
item_id number(11),
bill_id number(11),
service_id number(10),
cost number(13,2)
);
commit;

drop table student;
create table student(
id number(12) primary key,
name varchar2(20)
);

drop table course;
create table course(
id number(12) primary key,
name varchar2(30)
);
drop table stu_cour;
create table stu_cour(
stu_id number(12),
cour_id number(12)
);

insert into student values( 1,'����');
insert into student values( 2,'ŷ��');

insert into course values(1,'�������');
insert into course values(2,'�㷨');
insert into course values(3,'����ϵͳ');

insert into stu_cour values(1,2);
insert into stu_cour values(1,3);
insert into stu_cour values(2,3);
insert into stu_cour values(2,1);
commit;

drop table book;
create table book(
id number(10),
author varchar2(30),
publishing varchar2(30),
wordnumber varchar2(30),
totalpage varchar2(30)
);

drop table product;
create table product(
id number(10),
name varchar2(30),
price varchar2(10),
pic varchar2(30)
);
commit;

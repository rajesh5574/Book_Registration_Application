use book;
drop table bookdata;
create table BOOKDATA (
ID int primary key not null auto_increment ,
BOOKNAME VARCHAR(50) NULL,
AUTHORNAME VARCHAR(50) NULL,
BOOKEDITION VARCHAR(40) NULL,
BOOKPRICE FLOAT NULL
);
truncate table bookdata;
select*from bookdata;
--TO DROP THE TABLES
SET FOREIGN_KEY_CHECKS=0;
drop table dbi.accounts;
drop table dbi.tellers;
DROP TABLE dbi.branches;
SET FOREIGN_KEY_CHECKS=1;

-- create
create table dbi.branches
( branchid int not null,
  branchname char(20) not null,
  balance int not null,
  address char(72) not null,
  primary key (branchid));

create table dbi.accounts
( accid int not null,
  name char(20) not null,
  balance int not null,
  branchid int not null,
  address char(68) not null,
  primary key (accid),
  foreign key (branchid) references branches(branchid));

create table dbi.tellers
( tellerid int not null,
  tellername char(20) not null,
  balance int not null,
  branchid int not null,
  address char(68) not null,
  primary key (tellerid),
  foreign key (branchid) references branches(branchid));

create table dbi.history
( accid int not null,
  tellerid int not null,
  delta int not null,
  branchid int not null,
  accbalance int not null,
  cmmnt char(30) not null,
  foreign key (accid) references dbi.accounts(accid),
  foreign key (tellerid) references dbi.tellers(tellerid),
  foreign key (branchid) references branches(branchid));


-- Select all
select * from dbi.branches where 1=1;
select * from dbi.accounts where 1=1;
select * from dbi.tellers where 1=1;
select * from dbi.history where 1=1;

select count(*) from branches;
select count(*) from accounts;
select count(*) from tellers;

-- Delete all
delete from dbi.branches where 1=1;
delete from dbi.accounts where 1=1;
delete from dbi.tellers where 1=1;
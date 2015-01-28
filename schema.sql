DROP DATABASE IF EXISTS java_backend;
CREATE DATABASE java_backend;
 

USE java_backend;

/*==============================================================*/
/* Table: jc_region                                             */
/*==============================================================*/
create table jc_region
(
   region_id     int auto_increment,
   region_name   varchar(255) not null,
   primary key (region_id)
) engine=InnoDB;

create table jc_region_id
(
   region_id     int not null,
   region_name   varchar(255) not null
) engine=InnoDB;

create table jc_region_ext
(
   region_ext_id   int not null,
   region_leader   varchar(255) not null
) engine=InnoDB;

insert into jc_region (region_name) values ("Moscow");
insert into jc_region (region_name) values ("Voronezh");
insert into jc_region (region_name) values ("Altai");
insert into jc_region (region_name) values ("Komi");
insert into jc_region (region_name) values ("Krasnodar");

insert into jc_region_ext (region_ext_id, region_leader) values (1, "Sobyanin");
insert into jc_region_ext (region_ext_id, region_leader) values (2, "Gordeev");


create table jc_region_version
(
   region_id     int auto_increment,
   region_name   varchar(255) not null,
   version       int not null,
   primary key (region_id)
) engine=InnoDB;

insert into jc_region_version (region_name, version) values ("Moscow", 1);
insert into jc_region_version (region_name, version) values ("Voronezh", 1);


create table jc_city
(
   city_id       int auto_increment,
   city_name     varchar(255) not null,
   region_id     int not null,
   primary key (city_id)
) engine=InnoDB;

create index region_index on jc_city(region_id);

alter table jc_city add constraint ref_region_constraint 
   foreign key (region_id) 
   references jc_region(region_id);

insert into jc_city (city_name, region_id) values ("Kirov", 1);
insert into jc_city (city_name, region_id) values ("Arkhipovsk", 1);
insert into jc_city (city_name, region_id) values ("Lipetsk", 1);
insert into jc_city (city_name, region_id) values ("Kamensk", 2);
insert into jc_city (city_name, region_id) values ("Novovoronezh", 2);
insert into jc_city (city_name, region_id) values ("Sochi", 5);
insert into jc_city (city_name, region_id) values ("Kropotkin", 5);


create table jc_user
(
   first_name   varchar(255) not null,
   last_name    varchar(255) not null,
   middle_name  varchar(255) not null
) engine=InnoDB;

insert into jc_user (first_name, last_name, middle_name) values ("Fedor", "Akunin", "Petrovich");
insert into jc_user (first_name, last_name, middle_name) values ("Peter", "Pronin", "Glebovich");


create table jc_address
(
   user_id    int auto_increment,
   first_name   varchar(255) not null,
   last_name    varchar(255) not null,
   middle_name  varchar(255) not null,
   street  varchar(255),
   house     int,
   primary key (user_id)
) engine=InnoDB;

create table jc_catalog
(
   catalog_id    int auto_increment,
   catalog_name  varchar(255),
   parent_id     int,
   primary key (catalog_id)
) engine=InnoDB;

insert into jc_catalog (catalog_name, parent_id) values ("root", null);
insert into jc_catalog (catalog_name, parent_id) values ("first", 1);
insert into jc_catalog (catalog_name, parent_id) values ("second", 1);
insert into jc_catalog (catalog_name, parent_id) values ("first.first", 2);
insert into jc_catalog (catalog_name, parent_id) values ("first.second", 2);
insert into jc_catalog (catalog_name, parent_id) values ("first.first.first", 4);
insert into jc_catalog (catalog_name, parent_id) values ("first.first.second", 4);
insert into jc_catalog (catalog_name, parent_id) values ("first.first.third", 4);
insert into jc_catalog (catalog_name, parent_id) values ("first.second.first", 5);
insert into jc_catalog (catalog_name, parent_id) values ("first.second.second", 5);
insert into jc_catalog (catalog_name, parent_id) values ("second.first", 3);
insert into jc_catalog (catalog_name, parent_id) values ("second.second", 3);
insert into jc_catalog (catalog_name, parent_id) values ("second.third", 3);



create table jc_book
(
   book_id      int auto_increment,
   book_name    varchar(255),
   primary key (book_id)
) engine=InnoDB;

create table jc_author
(
   author_id      int auto_increment,
   author_name    varchar(255),
   primary key (author_id)
) engine=InnoDB;


create table jc_book_author
(
   book_id      int not null,
   author_id    int not null
) engine=InnoDB;


insert into jc_book (book_name) values ("Java for beginners");
insert into jc_book (book_name) values ("SQL for idiots");
insert into jc_book (book_name) values ("C# for beginners");

insert into jc_author (author_name) values ("Big Author");
insert into jc_author (author_name) values ("Small Author");
insert into jc_author (author_name) values ("Author");

insert into jc_book_author (book_id, author_id) values (1, 1);
insert into jc_book_author (book_id, author_id) values (1, 2);
insert into jc_book_author (book_id, author_id) values (2, 2);
insert into jc_book_author (book_id, author_id) values (2, 3);
insert into jc_book_author (book_id, author_id) values (3, 1);
insert into jc_book_author (book_id, author_id) values (3, 2);
insert into jc_book_author (book_id, author_id) values (3, 3);


create table jc_payment
(
   payment_id    int auto_increment,
   payment_type  int,
   amount        decimal,
   card_number   varchar(20),
   cash_desk     varchar(20),
   bank_id       varchar(20),
   primary key (payment_id)
) engine=InnoDB;

create table jc_credit_payment
(
   payment_id    int not null,
   card_number   varchar(20),
   primary key (payment_id)
) engine=InnoDB;

create table jc_cash_payment
(
   payment_id    int not null,
   cash_desk     varchar(20),
   primary key (payment_id)
) engine=InnoDB;

create table jc_cheque_payment
(
   payment_id    int not null,
   bank_id       varchar(20),
   primary key (payment_id)
) engine=InnoDB;

-- this tables for hibernate inheritance example (table-per-class)

create table jc_payment_full(
   payment_id    int auto_increment,
   amount        decimal,
   primary key (payment_id)
) engine=InnoDB;


create table jc_cash_payment_full(
   payment_id    int auto_increment,
   amount        decimal,
   cash_desk     varchar(20),
   primary key (payment_id)
) engine=InnoDB;

create table jc_cheque_payment_full(
   payment_id    int auto_increment,
   amount        decimal,
   bank_id       varchar(20),
   primary key (payment_id)
) engine=InnoDB;

create table jc_credit_payment_full(
   payment_id    int auto_increment,
   amount        decimal,
   card_number   varchar(20),
   primary key (payment_id)
) engine=InnoDB;

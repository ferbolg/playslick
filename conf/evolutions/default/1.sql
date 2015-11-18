# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Person (name VARCHAR(30) NOT NULL,surname VARCHAR(30) NOT NULL,email VARCHAR(30) NOT NULL);
alter table Person add constraint pk_person primary key(name,surname);

# --- !Downs

alter table Person drop constraint pk_person;
drop table Person;


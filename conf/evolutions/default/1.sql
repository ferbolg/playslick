# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "Person" ("name" VARCHAR NOT NULL,"surname" VARCHAR NOT NULL,"color" VARCHAR NOT NULL);
alter table "Person" add constraint "pk_person" primary key("name","surname");

# --- !Downs

alter table "Person" drop constraint "pk_person";
drop table "Person";


create SCHEMA nc_project;

SET search_path TO nc_project,public;

create sequence photos_id_seq
;

create sequence photos_spot_id_seq
;

create sequence photos_user_id_seq
;

create sequence quests_id_seq
;

create sequence spots_id_seq
;

create sequence spots_in_quests_photo_id_seq
;

create sequence spots_in_quests_quest_id_seq
;

create sequence spots_in_quests_spot_id_seq
;

create sequence spots_in_quests_spots_in_quests_id_seq
;

create sequence user_groups_id_seq
;

create sequence user_progress_quest_id_seq
;

create sequence user_progress_user_id_seq
;

create sequence user_progress_user_progress_id_seq
;

create sequence user_spot_progress_id_seq
;

create sequence user_spot_progress_spots_in_quests_id_seq
;

create sequence user_spot_progress_user_progress_id_seq
;

create sequence users_id_seq
;

create sequence photo_type_photo_type_id_seq
;

create sequence offer_id_seq
;

create sequence offer_category_id_seq
;

create sequence user_offers_id_seq
;

create sequence photo_photo_id_seq
;

create sequence photo_user_id_seq
;

create sequence photo_spot_id_seq
;

create sequence quest_quest_id_seq
;

create sequence spot_spot_id_seq
;

create sequence spot_in_quest_spots_in_quests_id_seq
;

create sequence spot_in_quest_spot_id_seq
;

create sequence spot_in_quest_quest_id_seq
;

create sequence spot_in_quest_photo_id_seq
;

create sequence user_group_group_id_seq
;

create sequence user_progress_user_id_seq1
;

create sequence user_progress_quest_id_seq1
;

create sequence user_progress_user_progress_id_seq1
;

create sequence user_spot_progress_id_seq1
;

create sequence user_spot_progress_spots_in_quests_id_seq1
;

create sequence user_spot_progress_user_progress_id_seq1
;

create sequence user_user_id_seq
;

create sequence photo_type_photo_type_id_seq1
;

create sequence offer_id_seq1
;

create sequence offer_category_id_seq1
;

create sequence user_offer_id_seq
;

create table photo
(
	photo_id bigserial not null
		constraint photos_pkey
			primary key,
	url varchar(250) not null,
	user_id bigserial not null,
	spot_id bigserial not null,
	upload_date date not null,
	type_id integer not null
)
;

create table quest
(
	quest_id bigserial not null
		constraint quests_pkey
			primary key,
	name varchar(250) not null,
	description varchar(250) not null,
	upload_date date not null,
	reward integer not null,
	owner_id bigint not null,
	number_of_participants integer default 10 not null,
	number_of_joiners integer default 0 not null,
	status integer default 0 not null,
	reports integer default 0 not null
)
;

create table spot
(
	spot_id bigserial not null
		constraint spots_pkey
			primary key,
	name varchar(250) not null,
	upload_date date not null,
	lat double precision not null,
	lng double precision not null
)
;

alter table photo
	add constraint spot_id
		foreign key (spot_id) references spot
;

create table spot_in_quest
(
	spots_in_quests_id serial not null
		constraint spots_in_quests_id
			primary key,
	spot_id serial not null
		constraint spot_id
			references spot,
	quest_id serial not null
		constraint quest_id
			references quest,
	photo_id serial not null
		constraint photo_id
			references photo
)
;

create table user_group
(
	group_id bigserial not null
		constraint user_groups_pkey
			primary key,
	name varchar(250) not null
)
;

create table user_progress
(
	user_id bigserial not null,
	quest_id bigserial not null
		constraint quest_id
			references quest,
	date_complete date,
	taking_date date,
	user_progress_id serial not null
		constraint user_progress_id
			primary key
)
;

create table user_spot_progress
(
	id serial not null
		constraint id
			primary key,
	spots_in_quests_id serial not null
		constraint spots_in_quests_id
			references spot_in_quest,
	user_progress_id serial not null
		constraint user_progress_id
			references user_progress,
	spot_status varchar(250) not null,
	date_complete date
)
;

create table "user"
(
	user_id bigserial not null
		constraint users_pkey
			primary key,
	group_id bigint not null
		constraint group_id
			references user_group,
	first_name varchar(250) not null,
	last_name varchar(250) not null,
	birthday date not null,
	email varchar(250) not null,
	registration_date date not null,
	password varchar(250) not null,
	balance bigint default 10 not null,
	avatar varchar(250),
	business_balance bigint,
	reports integer default 0 not null
)
;

alter table photo
	add constraint user_id
		foreign key (user_id) references "user"
;

alter table quest
	add constraint owner_id
		foreign key (owner_id) references "user"
;

alter table user_progress
	add constraint user_id
		foreign key (user_id) references "user"
;

create table photo_type
(
	photo_type_id serial not null
		constraint photo_type_pkey
			primary key,
	name varchar(60) not null
)
;

alter table photo
	add constraint photos_types_fk
		foreign key (type_id) references photo_type
;

create table offer
(
	id bigserial not null
		constraint offer_pkey
			primary key,
	name varchar(200) not null,
	category_id integer not null,
	owner_id integer not null
		constraint offer_user_user_id_fk
			references "user",
	amount integer not null,
	expire_date date not null,
	photo_url varchar(250),
	price integer
)
;

create unique index offer_id_uindex
	on offer (id)
;

create table offer_category
(
	id bigserial not null
		constraint offer_category_pkey
			primary key,
	name varchar(250) not null
)
;

create unique index offer_category_id_uindex
	on offer_category (id)
;

alter table offer
	add constraint offer_offer_category_id_fk
		foreign key (category_id) references offer_category
;

create table user_offer
(
	id bigserial not null
		constraint user_offers_pkey
			primary key,
	user_id integer not null
		constraint user_offer_user_user_id_fk
			references "user",
	offer_id integer not null
		constraint user_offer_offer_id_fk
			references offer
)
;

create unique index user_offers_id_uindex
	on user_offer (id)
;


SET search_path TO nc_project,public;

create sequence report_report_id_seq
;

create table report
(
	report_id serial not null
		constraint report_pkey
			primary key,
	user_id integer not null
		constraint report_user_fk
			references "user",
	quest_id integer not null
		constraint report_quest_fk
			references quest
)
;

create unique index report_report_id_uindex
	on report (report_id)
;


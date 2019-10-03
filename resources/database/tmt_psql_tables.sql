create table translation_entity(
id serial primary key,
source_language varchar(255) NOT NULL,
target_language varchar(255) NOT NULL,
source_text varchar(2048) NOT NULL,
target_text varchar(2048) NOT NULL,
creation_time timestamp NOT NULL
);
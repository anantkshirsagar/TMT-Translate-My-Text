create table translation_entity(
id bigint primary key auto_increment,
source_language varchar(255) NOT NULL,
target_language varchar(255) NOT NULL,
source_text varchar(2048) NOT NULL,
target_text varchar(2048) NOT NULL,
creation_time datetime NOT NULL
);


#create database translate_my_text;
create table Animal(id bigint not null AUTO_INCREMENT,
species varchar(20) not null,
PRIMARY KEY (id));

create table Pet(petId bigint not null AUTO_INCREMENT,
name varchar(20) not null,
PRIMARY KEY (petId),
CONSTRAINT `animal_id` FOREIGN KEY (`petId`) REFERENCES Animal (`id`) ON DELETE CASCADE ON UPDATE CASCADE);

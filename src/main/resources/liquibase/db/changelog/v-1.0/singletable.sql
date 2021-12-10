create table billing_details
(id bigint not null AUTO_INCREMENT,
 bd_type varchar(2),
 owner varchar(20),
 card_number integer,
 exp_month varchar(9),
 exp_year varchar(4),
 account integer,
 bank_name varchar(20),
 swift varchar(20),PRIMARY KEY (`id`));
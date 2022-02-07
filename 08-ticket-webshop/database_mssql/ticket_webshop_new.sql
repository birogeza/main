drop database if exists `ticket_webshop`;
create database if not exists `ticket_webshop`;
use `ticket_webshop`;

drop table if exists `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `sex` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

drop table if exists `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



drop table if exists `users_roles`;
 
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

drop table if exists `account`;

create table `account` (
	`id` int(11) not null auto_increment,
    `account_number` varchar(50) not null,
    `account_amount` double not null,
    `currency` varchar(5)  not null,
    `cvc_code` int(5)  not null,
    `user_id` int(11)  not null,
    primary key (`id`),
    constraint `fk_accounts_users` foreign key (`user_id`) references `user`(`id`) on delete cascade on update no action
)Engine=InnoDB auto_increment=1 default charset = utf8;

SET FOREIGN_KEY_CHECKS = 1;


drop table if exists `event`;

create table `event` (
	`id` int(11) not null auto_increment,
    `event_name` varchar(100) not null,
    `event_location` varchar(50) default null,
    `event_start` long not null,
    `event_start_string` varchar(255) default null,
    `event_end` long not null,
    `event_end_string` varchar(255) default null,
    `provider_id` int(11) default null,
    primary key (`id`),
    /*unique key `event_name_unique`(`event_name`),*/
    key `fk_user_event_idx`(`provider_id`)
--    ,constraint `fk_provider` foreign key (`provider_id`) references `user`(`id`) on delete no action on update no action
) Engine=InnoDB auto_increment=1 default charset = utf8;

SET FOREIGN_KEY_CHECKS = 1;


drop table if exists `seat`;

create table `seat` (
	`id` int(11) not null auto_increment,
    `seat_name` varchar(20) default null,
    `price` float default null,
    `reserved` boolean default false,
    `user_id` int(11) default null,
    `event_id` int(11) default null,
    primary key (`id`),
    key `fk_users_seats_idx`(`user_id`)
   ,constraint `fk_events_seat` foreign key (`event_id`) references `event`(`id`) on delete cascade on update cascade
)Engine=InnoDB auto_increment=1 default charset = utf8;

SET FOREIGN_KEY_CHECKS = 1;








/*
For test:
	User_names: john, mary, tom
    password: test123
    Bcrypt: $2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny
*/
/*
-	User törlése előtt a seat táblában null-ra kell változtatni a user_id-t ahol seat.user_id = user.id, amit törölni akarunk.
	így ha törlünk egy usert, akkor megmarad a szék foglalása, foglalt marad, de már nem fogjuk tudni, hogy ki foglalta le.
-	Ha user-t törlünk, akkor az account-ja is automatikusan törlődni fog.
-	Előbb létre kell hozni egy usert, és vásárolni csak akkor tud, majd ha van neki account-ja
	account.user_id = user.id
-	User törlése előtt előbb a users_roles-ól kell törölni az okat a sorokat, ahol user.id=users_roles.user_id
-	lehet Updatelni az accountot, lehet updatelni a user adatot
-	lehet törölni az account táblábóload
-	Lehet törölni User-t, attól az event megmarad, de nem fogjuk tudni, hogy ki volt a szervezője.
*/



insert into `user` values(1,'john', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','John','Smith','jsmith@xmail.com',35,'male');
insert into `user` values(2,'mary', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Mary','Jane','mjane@xmail.com',22,'female');
insert into `user` values(3,'tom', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Tom','Moore','tmoore@xmail.com',68,'male');
insert into `user` values(4,'geza', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Geza','Biro','geza_biro@xmail.com',36,'male');
insert into `user` values(5,'david', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','David','Beckham','davidbeckham@xmail.com',44,'male');
insert into `user` values(6,'pamela', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Pamela','Anderson','p_anderson@xmail.com',44,'female');

insert into `role` (`name`) values ('ROLE_USER'),('ROLE_PROVIDER'),('ROLE_ADMIN');

insert into `users_roles` values(1,1);
insert into `users_roles` values(2,1);
insert into `users_roles` values(2,2);
insert into `users_roles` values(3,1);
insert into `users_roles` values(3,3);
insert into `users_roles` values(4,3);
insert into `users_roles` values(4,2);
insert into `users_roles` values(4,1);
insert into `users_roles` values(5,1);
insert into `users_roles` values(5,2);
insert into `users_roles` values(6,1);


insert into `account` values(1,"1234-4567-7891-2345", 12000, "HUF", 456,1);
insert into `account` values(2,"9834-7867-3591-6545", 2000, "HUF", 753,6);

/*az eventet fel lehet vinni, de csak létező user_id-val*/

insert into `event` values (1,"Test Football Match","Puskas Ferenc Stadion",1664564400000,'2022/09/30 21:00:00',1664571600000,'2022/09/30 23:00:00',5);
insert into `event` values (2,"Test Exhibition","Magyar Nemzeti Muzeum",1657864800000,'2022/07/15 08:00:00',1661983199000,'2022/08/31 23:59:59',2);
insert into `event` values (3,"Test Metal Concert","Papp Laszlo Sportarena",1642707000000,'2022/01/20 20:30:00',1642732200000,'2022/01/21 03:30:00',2);

/*seat-et is fel lehet vinni, null-lal vagy user_id-val. Ha a reserved -> true, akkor kell user-id*/

insert into `seat` values(1,"MATCH_1",1000,true,1,1);
insert into `seat` values(2,"MATCH_1",1000,false,null,1);
insert into `seat` values(3,"MATCH_2",2000,true,6,1);
insert into `seat` values(4,"MATCH_2",2000,false,null,1);
insert into `seat` values(5,"MATCH_2",2000,false,null,1);

insert into `seat` values(6,"EXHIB_BRONZE",1500,true,6,2);
insert into `seat` values(7,"EXHIB_BRONZE",1500,false,null,2);
insert into `seat` values(8,"EXHIB_GOLD",2500,false,null,2);
insert into `seat` values(9,"EXHIB_GOLD",2500,false,null,2);
insert into `seat` values(10,"EXHIB_VIP",3500,false,null,2);

create or replace view users_events as
select 
	u.id as customer_id, 
	e.provider_id as provider_id, 
	e.id as event_id,
    s.id as seat_id,
	u.username,
	u.first_name, 
	u.last_name, 
	e.event_name, 
	e.event_location, 
	e.event_start_string, 
	s.seat_name, 
    s.reserved,
	s.price 
	
from 
	user u left outer join seat s 
		on u.id = s.user_id
	left outer join event e on e.id = s.event_id;


create or replace view providers_sales as
 select provider_id,event_name,sum(price) as price 
 from users_events
 group by provider_id,event_name;





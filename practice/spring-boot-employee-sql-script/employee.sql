create database if not exists ticket_webshop;

drop table if exists user;

create table user(
	id int(11) not null auto_increment,
    username varchar(50) not null,
    password char(80) not null,
    first_name varchar(50) default null,
    last_name varchar(50) default null,
    email varchar(50) not null,
    age int check (age > 0),
    sex varchar(20) not null default 'male',
    primary key (`id`)
) Engine=InnoDB auto_increment=1 default charset = utf8;

drop table if exists role;

create table `role`(
	`id` int(11) not null auto_increment,
	`name` varchar(50) default null,
    primary key(`id`)
)Engine=InnoDB auto_increment=1 default charset = utf8;

drop table if exists `users_roles`(
	`user_id` int(11) not null,
    `role_id` int(11) not null,
    primary key(`user_id`,`role_id`),
    
    key `fk_role_idx`(`role_id`),
    
    constraint `fk_user` foreign key(`user_id`)
    references `user` (`id`)
    on delete no action on update no action,
    
    constraint `fk_role` foreign key(`role_id`)
    references `role` (`id`)
    on delete no action on update no action,
)Engine=InnoDB default charset = utf8;




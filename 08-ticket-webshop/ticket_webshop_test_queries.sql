/*
select r.name 
from `role` r inner join users_roles ur
	on r.id = ur.role_id
    inner join `user` u on u.id = ur.user_id
where u.last_name='Moore';
*/
-- insert into `user` (id,username,password,first_name,last_name,email) values (5,'birogeza', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Geza','Biro','birog@xmail.com');

insert into `user` (id,username,password,first_name,last_name,email) values (6,'joskapista', '$2a$12$qJyzEcYl2lW1k0FcKU.H5.47w8bDsERBUtFeh3phRF2LkVX93eQny','Geza','Biro','birog@xmail.com');

select * from user;
select * from users_roles;
select * from role;
select * from event;
select * from seat;
select * from account;

delete from users_roles where user_id = 2;
delete from user where id=2;

delete from `account` where id in (4,5,6);

delete from user where id=6;

update `user` set age=50 where id=6;
commit;

update `account` set account_amount=5000 where id=2;
commit;

delete from `account` where id=2;

select sum(account_amount) from account a where a.user_id=1;

-- eredmény provider-enként
select e.event_name, e.event_location, e.event_start_string, u.first_name, u.last_name, s.seat_name, s.price, s.reserved
from event e inner join seat s on e.id = s.event_id inner join user u on s.user_id = u.id
where s.reserved = true and e.provider_id = 2;

-- userenkénti megvett jegyek listája
select u.first_name, u.last_name, e.event_name, e.event_location, e.event_start_string, s.seat_name, s.price
from event e inner join seat s on e.id = s.event_id inner join user u on s.user_id = u.id
where s.reserved = true and u.id = 6;

-- event-enkénti bevétel
select e.event_name, sum(s.price)
from event e inner join seat s on e.id = s.event_id inner join user u on u.id = e.provider_id
where s.reserved = true
	and u.id = 2
group by e.event_name;


select * from users_events where reserved = true and customer_id = 6;

 
select * from providers_sales;

delete from account where id=4;
commit;

select * from users_events where reserved=true and customer_id = 4;

select * from users_events where reserved=true and customer_id = 4;

CREATE DATABASE billetera_virtual;

SELECT * from roles;

# User 1
insert into roles (creation_date, update_date, role_desc, role_name) 
values ('2023-06-09', '2023-06-10',"Soy admin", "ADMIN");

INSERT INTO user (first_name, last_name, email, passwords, creation_date, update_date, soft_delete,role_id)
VALUES ('paco', 'Doe', 'johndoe@example.com', 'password123', '2023-06-09', '2023-06-09', false,1);

insert into account (balance,creation_date,soft_delete,transaction_limit,update_date,user_id,currency)
values(100,'2023-06-09',false,50,'2023-06-09',1,"dolares");

# User 2
insert into roles (creation_date, update_date, role_desc, role_name) 
values ('2023-06-12', '2023-06-12',"Soy admin", "ADMIN");

INSERT INTO user (first_name, last_name, email, passwords, creation_date, update_date, soft_delete, role_id)
VALUES ('Alex', 'Suarez', 'alexSuarez213@example.com', 'password123456', '2023-06-12', '2023-06-12', false, 2);

insert into account (balance, creation_date, soft_delete, transaction_limit, update_date, user_id, currency)
values(200, '2023-06-12', false, 50,'2023-06-12', 2, "pesos");

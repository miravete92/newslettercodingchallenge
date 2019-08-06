use db_newsletter;

create table newsletter(
	id int(11),
	name varchar(255),
	welcome_mail varchar(255)
);

insert into newsletter(id, name, welcome_mail)
values(1, 'Adidas challenge newsletter', '<h1>Welcome to Adidas challenge newsletter</h1><p>This is a formatted html text email</p>');

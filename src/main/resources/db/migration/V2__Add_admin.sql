insert into usr (id, username, password, active, email)
values ('a92bb84b-c1bc-4f48-a4b5-1db4556c3161', 'admin', 'admin', true,'sweaterRu@yandex.ru');

insert into user_role (user_id, roles)
values ('a92bb84b-c1bc-4f48-a4b5-1db4556c3161', 'USER'), ('a92bb84b-c1bc-4f48-a4b5-1db4556c3161', 'ADMIN');
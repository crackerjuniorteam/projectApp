insert into pack (id, author_id, created, is_public, likes, name, parent_id)
values ('1daa9a1a-2cc7-432f-bd12-14a717413269', 'a92bb84b-c1bc-4f48-a4b5-1db4556c3161', current_timestamp , true, 0, 'MorseCode', 0);



insert into card (id, answer, question, user_id)
values ('b5a8c6bd-c3d9-4616-a8bd-5230b1d68cc4', '• _', 'A' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('0b2f445a-6967-4b51-a140-7724fee259ec', '_ • • •', 'B' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('bb4b85cf-d6dd-4bef-995d-279d34090f52', 'B', '_ • • •' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('5651fb4a-d927-4e01-8d6b-82d17c6be658', '_ • _ •', 'C' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('462f5e16-fae8-497c-b9c5-7ec5da1cad56', 'C', '_ • _ •' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('76d677ef-f8cb-4a66-9a10-ce854de9952f', '_ • •', 'D' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('97f19d7d-a466-4530-8a04-e003b6b3e39b', 'D', '_ • •' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('2e137563-2cb3-49af-a429-d414c6ecf691', '•', 'E' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('7711d813-2ffa-4c56-9aba-9bc2c0c16fa8', 'E', '•' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('d174f84d-531d-423d-afa3-8b76ad4ed6b9', '• • _ •', 'F' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );

insert into card (id, answer, question, user_id)
values ('6e5c8ebf-aade-4e48-bd1a-c3c658f7de33', 'F', '• • _ •' ,'a92bb84b-c1bc-4f48-a4b5-1db4556c3161' );


insert into card_in_pack (card_id, pack_id) values ('b5a8c6bd-c3d9-4616-a8bd-5230b1d68cc4', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('0b2f445a-6967-4b51-a140-7724fee259ec', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('bb4b85cf-d6dd-4bef-995d-279d34090f52', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('5651fb4a-d927-4e01-8d6b-82d17c6be658', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('462f5e16-fae8-497c-b9c5-7ec5da1cad56', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('76d677ef-f8cb-4a66-9a10-ce854de9952f', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('97f19d7d-a466-4530-8a04-e003b6b3e39b', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('2e137563-2cb3-49af-a429-d414c6ecf691', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('7711d813-2ffa-4c56-9aba-9bc2c0c16fa8', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('d174f84d-531d-423d-afa3-8b76ad4ed6b9', '1daa9a1a-2cc7-432f-bd12-14a717413269');
insert into card_in_pack (card_id, pack_id) values ('6e5c8ebf-aade-4e48-bd1a-c3c658f7de33', '1daa9a1a-2cc7-432f-bd12-14a717413269');










INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');


INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');


INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);

INSERT INTO CLIENT(id, name) VALUES (1, 'Raul Fernandez');
INSERT INTO CLIENT(id, name) VALUES (2, 'Jose Luis Gonzalez');
INSERT INTO CLIENT(id, name) VALUES (3, 'Ramon Ruiz');
INSERT INTO CLIENT(id, name) VALUES (4, 'Luisa Ramos');

INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (1, 1, 1, '2022-4-17', '2022-4-22');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (2, 2, 2, '2022-4-17', '2022-4-23');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (3, 3, 3, '2022-4-17', '2022-4-24');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (4, 4, 4, '2022-4-17', '2022-4-25');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (5, 5, 1, '2022-4-23', '2022-4-30');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (6, 1, 2, '2022-4-24', '2022-5-1');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (7, 2, 3, '2022-4-25', '2022-5-2');
INSERT INTO LEASE(id, game_id, client_id, start_date, end_date) VALUES (8, 3, 4, '2022-4-26', '2022-5-3');
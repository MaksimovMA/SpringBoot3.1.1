INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO users (age, email, firstName, lastName, password) VALUES (26, 'maksimov@gmail.com', 'Maksim', 'Maksimov', 'admin');
INSERT INTO users (age, email, firstName, lastName, password) VALUES (30, 'german@gmail.com', 'German', 'Mentor', 'user');
INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1);
INSERT INTO users_roles (users_id, roles_id) VALUES (1, 2);
INSERT INTO users_roles (users_id, roles_id) VALUES (2, 2);
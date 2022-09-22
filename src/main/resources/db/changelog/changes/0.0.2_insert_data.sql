INSERT INTO t_categories (name)
    VALUES ('Programming'),
           ('Web Design'),
           ('Project Management');

INSERT INTO t_courses (name, description, price, rating, author_id, category_id, content_url)
    VALUES ('Course of Programming', 'This Course destination for beginner and professional developers', 400000.00, 0, NULL, 1, NULL),
           ('Course of Web Design', 'It is the best course about Web Design', 300000.00, 0, NULL, 2, NULL),
           ('Course of Project Management', 'This course dedicated from Project Teams and TOP Managers', 500000.00, 0, NULL, 3, NULL);

INSERT INTO t_roles (name, role)
    VALUES ('ADMINISTRATOR', 'ROLE_ADMIN'),
           ('TEACHER', 'ROLE_TEACHER'),
           ('USER', 'ROLE_USER');

INSERT INTO t_users (email, full_name, password, avatar_url)
    VALUES ('admin@gmail.com', 'Admin Admin', '$2a$10$o/tOmfIW1GKv8iWSbKg2COLITfaXs1/GSN4798XPd5Si/fFr2YbCK', NULL),
           ('teacher@gmail.com', 'Teacher Teacher', '$2a$10$brVcXeKC3Txz0iP8HzFJsuc9ITXkC8K0REaWgoYtv34duKJEnW3vW', NULL),
           ('user@gmail.com', 'User User', '$2a$10$g9CTsDuSmhZmFetysjow4uwJA/JJnjWUnvZiSqscc/J92yFQZg4fi', NULL);

INSERT INTO t_users_roles (user_id, roles_id)
    VALUES (1, 1),
           (1, 3),
           (2, 2),
           (2, 3),
           (3, 3);

INSERT INTO t_enroll_cards (comment, rating, point, course_id, user_id)
VALUES (NULL, 2, 0, 1, 2),
       ('Its good course 2', 3, 0, 2, 2),
       ('Its course 2 useful', 3, 0, 2, 3),
       ('Its good course 3', 4, 0, 3, 3);
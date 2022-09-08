INSERT INTO t_categories (name)
    VALUES ('Programming'),
           ('Web Design'),
           ('Project Management');

INSERT INTO t_courses (name, description, price, rating, author_id, category_id, content_url)
    VALUES ('Course of Programming', 'This Course destination for beginner and professional developers', 400000.00, 4, 1, 1, ''),
           ('Course of Web Design', 'It is the best course about Web Design', 300000.00, 3, 1, 2, ''),
           ('Course of Project Management', 'This course dedicated from Project Teams and TOP Managers', 500000.00, 5, 1, 3, '');

INSERT INTO t_roles (role)
    VALUES ('ROLE_ADMIN'),
           ('ROLE_TEACHER'),
           ('ROLE_USER');

INSERT INTO t_users (email, full_name, password)
    VALUES ('admin@gmail.com', 'Administrator', '$2a$10$o/tOmfIW1GKv8iWSbKg2COLITfaXs1/GSN4798XPd5Si/fFr2YbCK'),
           ('teacher@gmail.com', 'Teacher', '$2a$10$brVcXeKC3Txz0iP8HzFJsuc9ITXkC8K0REaWgoYtv34duKJEnW3vW'),
           ('user@gmail.com', 'User', '$2a$10$g9CTsDuSmhZmFetysjow4uwJA/JJnjWUnvZiSqscc/J92yFQZg4fi');

INSERT INTO t_users_roles (user_id, roles_id)
    VALUES (1, 1),
           (1, 2),
           (1, 3),
           (2, 2),
           (3, 3);
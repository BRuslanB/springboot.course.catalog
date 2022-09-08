ALTER TABLE t_courses
    DROP CONSTRAINT IF EXISTS course_category_id_fk;
ALTER TABLE t_courses
    ADD CONSTRAINT course_category_id_fk
    FOREIGN KEY (category_id) REFERENCES t_categories;

ALTER TABLE t_courses
    DROP CONSTRAINT IF EXISTS course_author_id_fk;
ALTER TABLE t_courses
    ADD CONSTRAINT course_author_id_fk
        FOREIGN KEY (author_id) REFERENCES t_users;

ALTER TABLE t_users_roles
    DROP CONSTRAINT IF EXISTS user_roles_id_fk;
ALTER TABLE t_users_roles
    ADD CONSTRAINT user_roles_id_fk
        FOREIGN KEY (user_id) REFERENCES t_users;

ALTER TABLE t_users_roles
    DROP CONSTRAINT IF EXISTS roles_user_id_fk;
ALTER TABLE t_users_roles
    ADD CONSTRAINT roles_user_id_fk
        FOREIGN KEY (roles_id) REFERENCES t_roles;
DROP TABLE IF EXISTS t_categories CASCADE;
CREATE TABLE t_categories(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL
);

DROP TABLE IF EXISTS t_courses CASCADE;
CREATE TABLE t_courses(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    price numeric(10,2) DEFAULT 0,
    rating INT DEFAULT 0,
    author_id INT,
    category_id INT,
    content_url TEXT
);

DROP TABLE IF EXISTS t_roles CASCADE;
CREATE TABLE t_roles(
    id SERIAL PRIMARY KEY NOT NULL,
    role TEXT NOT NULL
);

DROP TABLE IF EXISTS t_users CASCADE;
CREATE TABLE t_users(
    id SERIAL PRIMARY KEY NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    full_name TEXT NOT NULL,
    avatar_url TEXT
);

DROP TABLE IF EXISTS t_users_roles;
CREATE TABLE t_users_roles(
    user_id INT NOT NULL,
    roles_id INT NOT NULL
);

DROP TABLE IF EXISTS t_enroll_cards;
CREATE TABLE t_enroll_cards(
      id SERIAL PRIMARY KEY NOT NULL,
      comment TEXT,
      rating INT DEFAULT 0,
      course_id INT NOT NULL,
      user_id INT NOT NULL
);
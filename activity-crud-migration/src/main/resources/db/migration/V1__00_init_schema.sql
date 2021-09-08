CREATE TABLE IF NOT EXISTS user_info
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    display_name VARCHAR,
    username VARCHAR UNIQUE,
    password VARCHAR,
    email VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS activity_info
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR,
    creator VARCHAR,
    id_creator VARCHAR,
    description VARCHAR,
    category VARCHAR,
    date_org VARCHAR,
    time_org VARCHAR,
    venue VARCHAR,
    city VARCHAR
);

CREATE TABLE IF NOT EXISTS follow_activity_info
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    id_user BIGSERIAL NOT NULL,
    id_activity BIGSERIAL NOT NULL
);
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
    id_host_user BIGINT NOT NULL,
    description VARCHAR,
    category VARCHAR,
    date_org Date,
    time_org Time,
    venue VARCHAR,
    city VARCHAR,
    CONSTRAINT activity_user_user_id_fkey
        FOREIGN KEY(id_host_user)
            REFERENCES user_info(id)
    );

CREATE TABLE IF NOT EXISTS activity_user_info
(
    user_id BIGINT NOT NULL,
    activity_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, activity_id),
    CONSTRAINT fk_activity_user_info_user_user_id_fkey
        FOREIGN KEY(user_id)
            REFERENCES user_info(id),
    CONSTRAINT fk_activity_user_info_activity_user_id_fkey
        FOREIGN KEY(activity_id)
            REFERENCES activity_info(id)
    );
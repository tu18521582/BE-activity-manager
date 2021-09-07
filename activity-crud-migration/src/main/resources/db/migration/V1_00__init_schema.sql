CREATE TABLE IF NOT EXISTS activity_user
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    username  VARCHAR NOT NULL,
    first_name  VARCHAR,
    last_name  VARCHAR,
    email  VARCHAR NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS activity
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    activity_name   VARCHAR,
    activity_description  VARCHAR,
    user_id  BIGINT NOT NULL
    CONSTRAINT user_id_fkey REFERENCES activity_user(id),
    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE);

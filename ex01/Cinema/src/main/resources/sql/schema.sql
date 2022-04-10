DROP TABLE users;
CREATE TABLE users
(
    id        bigserial,
    firstname text,
    lastname  text,
    password  text,
    phone     text,
    PRIMARY KEY (id)
);
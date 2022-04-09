DROP TABLE users;
CREATE TABLE users
(
    id        bigserial,
    firstname varchar(50),
    lastname  varchar(50),
    password  varchar(50),
    phone     varchar(50),
    PRIMARY KEY (id)
);
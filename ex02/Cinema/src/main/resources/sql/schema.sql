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

DROP TABLE authentications;
CREATE TABLE authentications
(
    id       bigserial,
    idUser   bigint,
    date     timestamp,
    ip       text,
    password text,
    FOREIGN KEY (idUser) REFERENCES users (id),
    PRIMARY KEY (id)
);
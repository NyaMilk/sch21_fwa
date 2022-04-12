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
    date     timestamp DEFAULT CURRENT_TIMESTAMP,
    ip       text,
    FOREIGN KEY (idUser) REFERENCES users (id),
    PRIMARY KEY (id)
);
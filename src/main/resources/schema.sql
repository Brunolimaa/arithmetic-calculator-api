CREATE TABLE operations (
    id BIGINT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    cost DOUBLE NOT NULL
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    balance DOUBLE NOT NULL
);
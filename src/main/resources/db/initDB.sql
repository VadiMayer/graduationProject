DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant_dishes;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE UNIQUE INDEX restaurants_unique_email ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idr UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id     INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name   VARCHAR NOT NULL,
    rating INTEGER NOT NULL
);

CREATE UNIQUE INDEX restaurants_unique_name ON restaurants (name);

CREATE TABLE restaurant_dishes
(
    restaurant_id INTEGER                 NOT NULL,
    name          VARCHAR                 NOT NULL,
    cost          INTEGER                 NOT NULL,
    update_date   TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX restaurant_dishes_name ON restaurant_dishes (name);
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurant_dishes;
DELETE
FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('VadimUserAdmin', 'vadim@gmail.com', '{noop}vadim15'),
       ('MuratUser', 'murat@gmail.com', '{noop}murat60'),
       ('SergeyUser', 'sergey@gmail.com', '{noop}sergey'),
       ('KatyGuest', 'guest1@gmail.com', '{noop}katy5'),
       ('VikaGuest', 'guest2@gmail.com', '{noop}vika6');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100000),
       ('USER', 100001),
       ('USER', 100002);

INSERT INTO restaurants (name)
VALUES ('White rabbit'),
       ('Sixty'),
       ('Русский паб'),
       ('АндерСон'),
       ('Обломов');

INSERT INTO restaurant_dishes (description, cost, restaurant_id, update_date)
VALUES ('Фрикадельки "Мисьён"', 1850, 100005, '2022-04-10'),
       ('Суп "Жабо"', 1400, 100005, CURRENT_DATE),
       ('Коктейль "Агара"', 760, 100005, CURRENT_DATE),
       ('Котлеты "Банпулье"', 1540, 100006, CURRENT_DATE),
       ('Салат "Жандарм"', 470, 100006, '2022-04-10'),
       ('Суп "Аладин"', 720, 100007, CURRENT_DATE),
       ('Второе "Плов"', 910, 100008, CURRENT_DATE),
       ('Суп "Анастасия"', 847, 100009, '2022-04-10'),
       ('Фрукты "Питахайя"', 260, 100009, CURRENT_DATE);

INSERT INTO votes(user_id, restaurant_id, datevote)
VALUES (100000, 100005, CURRENT_DATE),
       (100001, 100006, '2022-04-10'),
       (100002, 100005, '2022-04-11');
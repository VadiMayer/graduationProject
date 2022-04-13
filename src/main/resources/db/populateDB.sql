DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant_dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('VadimUserAdmin', 'vadim@gmail.com', 'vadim15'),
       ('MuratUser', 'murat@gmail.com', 'murat60'),
       ('SergeyUser', 'sergey@gmail.com', 'sergey'),
       ('KatyGuest', 'guest1@gmail.com', 'katy5'),
       ('VikaGuest', 'guest2@gmail.com', 'vika6');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100000),
       ('USER', 100001),
       ('USER', 100002);

INSERT INTO restaurants (name, rating)
VALUES ('White rabbit', 201),
       ('Sixty', 153),
       ('Русский паб', 102),
       ('АндерСон', 126),
       ('Обломов', 145);

INSERT INTO restaurant_dishes (name, cost, restaurant_id, update_date)
VALUES ('Суп "Жабо"', 1400, 100005, now()),
       ('Фрикадельки "Мисьён"', 1850, 100005, '2022-04-10 10:00:00'),
       ('Коктейль "Агара"', 760, 100005, now()),
       ('Котлеты "Банпулье"', 1540, 100006, now()),
       ('Салат "Жандарм"', 470, 100006, '2022-04-10 10:00:00'),
       ('Суп "Аладин"', 720, 100007, now()),
       ('Второе "Плов"', 910, 100008, now()),
       ('Суп "Анастасия"', 847, 100009, '2022-04-10 10:00:00'),
       ('Фрукты "Питахайя"', 260, 100009, now());
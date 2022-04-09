DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant_dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 10000;

INSERT INTO users (name, email, password)
VALUES ('VadimUserAdmin', 'vadim@gmail.com', 'vadim15'),
       ('MuratUser', 'murat@gmail.com', 'murat60'),
       ('SergeyUser', 'sergey@gmail.com', 'sergey'),
       ('KatyGuest', 'guest1@gmail.com', 'katy5'),
       ('VikaGuest', 'guest2@gmail.com', 'vika6');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 10000),
       ('ADMIN', 10000),
       ('USER', 10001),
       ('USER', 10002);

INSERT INTO restaurants (name, rating)
VALUES ('White rabbit', 201),
       ('Sixty', 153),
       ('Русский паб', 102),
       ('АндерСон', 126),
       ('Обломов', 145);

INSERT INTO restaurant_dishes (name, cost, restaurant_id, update_date)
VALUES ('Суп "Жабо"', 1400, 10005, now()),
       ('Фрикадельки "Мисьён"', 1850, 10005, now()),
       ('Коктейль "Агара"', 760, 10005, now()),
       ('Котлеты "Банпулье"', 1540, 10006, now()),
       ('Салат "Жандарм"', 470, 10006, now()),
       ('Суп "Аладин"', 720, 10007, now()),
       ('Второе "Плов"', 910, 10008, now()),
       ('Суп "Анастасия"', 847, 10009, now()),
       ('Фрукты "Питахайя"', 260, 10009, now());
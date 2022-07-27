Design and implement a REST API using Hibernate/Spring/SpringMVC without frontend.

The task is:

Build a voting system for deciding where to have lunch.

○ 2 types of users: admin and regular users

○ Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

○ Menu changes each day (admins do the updates)

○ Users can vote on which restaurant they want to have lunch at

○ Only one vote counted per user

○ If user votes again the same day:

	◦ If it is before 11:00 we assume that he changed his mind.
    ◦ If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

Credentials for Swagger2: 

|  Credentials    |      Loggin     |   Password   |
|-----------------|-----------------|--------------|
|                 |                 |              |
|      ADMIN      | vadim@gmail.com |    vadim15   |
|                 |                 |              |
|                 |                 |              |
|      USER       | murat@gmail.com |    murat60   |
|                 |                 |              |

API endpoints

http://localhost:8080/v2/api-docs

URL = http://localhost:8080/

| API         | Method | Description                                   | URL                                       | User           |
|-------------|--------|-----------------------------------------------|-------------------------------------------|----------------|
|    Users    | GET    | Get all users                                 | {URL}/users/admin                         | Admin          |
|             | POST   | Register profile                              | {URL}/users/register                      | Unauthorized   |
|   Profile   | PUT    | Update profile                                | {URL}/users/profile                       | Authorized     |
|             | DELETE | Delete profile                                | {URL}/users/profile                       | Authorized     |
| Restaurants | GET    | Get all restaurants                           | {URL}/users/restaurants                   | Authorized     |
|             | POST   | Create restaurant					           | {URL}/users/admin/restaurants             | Admin          |
|             | PUT    | Update restaurant                             | {URL}/users/admin/restaurants{id}         | Admin          |
|             | DELETE | Delete restaurant                             | {URL}/users/admin/restaurants{id}         | Admin          |
|   Dishes    | GET    | Get all dishes                                | {URL}/users/admin/dishes                  | Admin          |
|             | POST   | Create dish                                   | {URL}/users/admin/dishes/restaurants/{id} | Admin          |
|             | PUT    | Update dish                                   | {URL}/users/admin/dishes/{id}             | Admin          |
|             | DELETE | Delete dish                                   | {URL}/users/admin/dishes/{id}             | Admin          |
|   Votes     | GET    | Get all for restaurant                        | {URL}/users/votes/restaurants/{id}        | Authorized     |
|             | POST   | Create vote for restaurant                    | {URL}/users/votes/restaurants/{id}        | Authorized     |

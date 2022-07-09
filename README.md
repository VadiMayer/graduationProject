API endpoints

URL = http://localhost:8080/

| API         | Method | Description                                   | URL                                       | User           |
|-------------|--------|-----------------------------------------------|-------------------------------------------|----------------|
| Restaurants | GET    | Get all restaurants                           | {URL}/users/restaurants                   | Anybody        |
|             | POST   | Create restaurant					           | {URL}/users/restaurants                   | Admin          |
|             | PUT    | Update restaurant                             | {URL}/users/restaurants/{id}              | Admin          |
|             | DELETE | Delete restaurant                             | {URL}/users/restaurants/{id}              | Admin          |
|   Dishes    | GET    | Get all dishes                                | {URL}/users/dishes                        | Admin          |
|             | POST   | Create dish, id of restaurant takes in DishTo | {URL}/users/dishes/restaurants            | Admin          |
|             | DELETE | Delete dish                                   | {URL}/users/dishes/{id}                   | Admin          |
|   Votes     | GET    | Get all for restaurant                        | {URL}/users/votes/restaurants/{id}        | Authorized     |
|             | POST   | Create vote for restaurant                    | {URL}/users/votes/restaurants/{id}        | Authorized     |
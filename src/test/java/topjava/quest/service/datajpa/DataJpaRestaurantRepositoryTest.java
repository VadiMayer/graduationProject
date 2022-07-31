package topjava.quest.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.DishRepository;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.RestaurantService;

import static topjava.quest.RestaurantTestData.*;

public class DataJpaRestaurantRepositoryTest extends AbstractServiceTest {

    @Autowired
    RestaurantService serviceRestaurant;

    @Autowired
    DishRepository serviceDish;

    @Test
    public void save() {
    }

    @Test
    public void getAllRestaurants() {
        serviceRestaurant.getAllRestaurants();

    }

    @Test
    public void getBetweenRating() {
    }

    @Test
    public void getWithDishes() {
        Restaurant restaurant = serviceRestaurant.getWithDishes(rests.get(0).getRestaurant_id());
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(restaurant, rests.get(0));
    }
}
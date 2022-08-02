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
        Restaurant created = serviceRestaurant.create(new Restaurant(null, "New Restaurant"));
        int newId = created.id();
        Restaurant newRestaurant = new Restaurant(newId, "New Restaurant");
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(serviceRestaurant.get(newId), newRestaurant);
    }

    @Test
    public void getAllRestaurants() {
        RESTAURANT_MATCHER.assertMatch(serviceRestaurant.getAllRestaurants(), rests);
    }

    @Test
    public void getWithDishes() {
        Restaurant restaurant = serviceRestaurant.getWithDishes(RESTAURANT_ID);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(restaurant, RESTAURANT_100005);
    }
}
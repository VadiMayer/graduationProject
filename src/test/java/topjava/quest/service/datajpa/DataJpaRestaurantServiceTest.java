package topjava.quest.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.DishRepository;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.RestaurantService;
import topjava.quest.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static topjava.quest.data.RestaurantTestData.*;

public class DataJpaRestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService serviceRestaurant;

    @Autowired
    DishRepository serviceDish;

    @Test
    void save() {
        Restaurant created = serviceRestaurant.create(new Restaurant(null, "New Restaurant"));
        int newId = created.id();
        Restaurant newRestaurant = new Restaurant(newId, "New Restaurant");
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(serviceRestaurant.get(newId), newRestaurant);
    }

    @Test
    void getAllRestaurants() {
        RESTAURANT_MATCHER.assertMatch(serviceRestaurant.getAllRestaurants(), rests);
    }

    @Test
    void getWithDishes() {
        Restaurant restaurant = serviceRestaurant.getWithDishes(RESTAURANT_ID);
        RESTAURANT_WITH_DISHES_MATCHER.assertMatch(restaurant, RESTAURANT_100005);
    }

    @Test
    void createWithException() {
        validateRootCause(ConstraintViolationException.class, ()-> serviceRestaurant.create(new Restaurant(null, " ")));
        validateRootCause(ConstraintViolationException.class, ()-> serviceRestaurant.create(new Restaurant(null, "b")));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> serviceRestaurant.delete(NOT_FOUND));
    }
}
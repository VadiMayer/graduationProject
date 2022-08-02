package topjava.quest.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.Dish;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.DishService;
import topjava.quest.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static topjava.quest.DishTestData.DISH_MATCHER;
import static topjava.quest.DishTestData.dishList;
import static topjava.quest.RestaurantTestData.RESTAURANT;
import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class DataJpaDishRepositoryTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    void getAllDishesForRestaurants() {
        DISH_MATCHER.assertMatch(dishService.getAll(), dishList);
    }

    @Test
    void delete() {
        dishService.delete(START_SEQ + 10);
        assertThrows(NotFoundException.class, () -> dishService.get(START_SEQ + 10));
    }

    @Test
    void create() {
        Dish created = dishService.create(new Dish(null, "Пончики", 1004, LocalDate.now(), RESTAURANT), RESTAURANT.getRestaurant_id());
        int newId = created.id();
        Dish newDish = new Dish(newId, "Пончики", 1004, LocalDate.now(), RESTAURANT);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }

    @Test
    void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> dishService.create(new Dish(null, "   ", 1004, LocalDate.now(), RESTAURANT), RESTAURANT.getRestaurant_id()));
        validateRootCause(ConstraintViolationException.class, () -> dishService.create(new Dish(null, "Пончики", 1004, null, RESTAURANT), RESTAURANT.getRestaurant_id()));
        validateRootCause(ConstraintViolationException.class, () -> dishService.create(new Dish(null, "Пончики", 0, LocalDate.now(), RESTAURANT), RESTAURANT.getRestaurant_id()));
    }
}

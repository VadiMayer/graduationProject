package topjava.quest.service.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.DishService;

import static topjava.quest.DishTestData.DISH_MATCHER;
import static topjava.quest.DishTestData.dishList;

public class DataJpaDishRepositoryTest extends AbstractServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    public void getAllForRestaurant() {
        DISH_MATCHER.assertMatch(dishService.getAll(), dishList);
    }
}

package topjava.quest.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import topjava.quest.service.AbstractServiceTest;
import topjava.quest.service.DishService;

import static topjava.quest.DishTestData.DISH_MATCHER;
import static topjava.quest.DishTestData.dishList;

@ActiveProfiles("datajpa")
public class DataJpaDishRepositoryTest extends AbstractServiceTest {

    DishService dishService;

    @Test
    public void getAllForRestaurant() {
        DISH_MATCHER.assertMatch(dishService.getAll(), dishList);
    }
}

package topjava.quest;

import topjava.quest.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static topjava.quest.DishTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = null;

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER = MatcherFactory.usingAssertions(Restaurant.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().isEqualTo(e),
            (a, e) -> {
                throw new UnsupportedOperationException();
            });

    public static final int NOT_FOUND = 10;

    public static final List<Restaurant> rests = List.of
            (
                    new Restaurant(START_SEQ + 5, "White rabbit", new ArrayList<>()),
                    new Restaurant(START_SEQ + 6, "Sixty", new ArrayList<>()),
                    new Restaurant(START_SEQ + 7, "Русский паб", new ArrayList<>()),
                    new Restaurant(START_SEQ + 8, "АндерСон", new ArrayList<>()),
                    new Restaurant(START_SEQ + 9, "Обломов", new ArrayList<>())
            );

    static {
        rests.get(0).setMenu(dishesWhiteRabbit);
    }

}

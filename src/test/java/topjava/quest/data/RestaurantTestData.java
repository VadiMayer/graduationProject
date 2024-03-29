package topjava.quest.data;

import topjava.quest.MatcherFactory;
import topjava.quest.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static topjava.quest.data.DishTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER = MatcherFactory.usingAssertions(Restaurant.class,
            (a, e) -> assertThat(a).usingRecursiveComparison().isEqualTo(e),
            (a, e) -> {
                throw new UnsupportedOperationException();
            });

    public static final int RESTAURANT_ID = START_SEQ + 5;
    public static final Restaurant RESTAURANT = new Restaurant(START_SEQ + 20, "New restaurant");
    public static final Restaurant RESTAURANT_100005 = new Restaurant(START_SEQ + 5, "White rabbit");
    public static final Restaurant RESTAURANT_100006 = new Restaurant(START_SEQ + 6, "Sixty");
    public static final Restaurant RESTAURANT_100007 = new Restaurant(START_SEQ + 7, "Русский паб");
    public static final int NOT_FOUND = 10;

    static {
        RESTAURANT_100005.setMenu(dishesWhiteRabbit);
    }

    public static final List<Restaurant> rests = List.of
            (
                    RESTAURANT_100006,
                    RESTAURANT_100005,
                    new Restaurant(START_SEQ + 8, "АндерСон", new ArrayList<>()),
                    new Restaurant(START_SEQ + 9, "Обломов", new ArrayList<>()),
                    RESTAURANT_100007
            );
}

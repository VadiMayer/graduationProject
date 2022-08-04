package topjava.quest.data;

import topjava.quest.MatcherFactory;
import topjava.quest.model.Dish;

import java.time.LocalDate;

import java.util.List;

import static topjava.quest.data.RestaurantTestData.RESTAURANT_100005;
import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final List<Dish> dishList = List.of
            (
                    new Dish(START_SEQ + 10, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDate.of(2022, 4, 10)),
                    new Dish(START_SEQ + 11, "Суп \"Жабо\"", 1400, 100005, LocalDate.now()),
                    new Dish(START_SEQ + 12, "Коктейль \"Агара\"", 760, 100005, LocalDate.now()),
                    new Dish(START_SEQ + 13, "Котлеты \"Банпулье\"", 1540, 100006, LocalDate.now()),
                    new Dish(START_SEQ + 14, "Салат \"Жандарм\"", 470, 100006, LocalDate.of(2022, 4, 10)),
                    new Dish(START_SEQ + 15, "Суп \"Аладин\"", 720, 100007, LocalDate.now()),
                    new Dish(START_SEQ + 16, "Второе \"Плов\"", 910, 100008, LocalDate.now()),
                    new Dish(START_SEQ + 17, "Суп \"Анастасия\"", 847, 100009, LocalDate.of(2022, 4, 10)),
                    new Dish(START_SEQ + 18, "Фрукты \"Питахайя\"", 260, 100009, LocalDate.now())
            );

    public static final List<Dish> dishesWhiteRabbit = List.of
            (
                    new Dish(START_SEQ + 11, "Суп \"Жабо\"", 1400, LocalDate.now(), RESTAURANT_100005),
                    new Dish(START_SEQ + 12, "Коктейль \"Агара\"", 760, LocalDate.now(), RESTAURANT_100005),
                    new Dish(START_SEQ + 10, "Фрикадельки \"Мисьён\"", 1850, LocalDate.of(2022, 4, 10),RESTAURANT_100005)
            );

}

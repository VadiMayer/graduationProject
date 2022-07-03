package topjava.quest.util;

import org.junit.Test;
import topjava.quest.DishTestData;
import topjava.quest.RestaurantTestData;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.to.DishTo;
import topjava.quest.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.*;

import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class RestaurantsUtilTest {

    public static final List<Dish> dishList = List.of(
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDateTime.of(2022, 4, 14, 0, 0)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDateTime.of(2022, 4, 14, 0, 0)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDateTime.of(2022, 4, 14, 0, 0)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDateTime.of(2022, 4, 14, 0, 0)),
            new Dish(START_SEQ + 11, "Фрикадельки \"Мисьён\"", 1850, 100005, LocalDateTime.of(2022, 4, 14, 0, 0))
    );

    public static void main(String[] args) {

        System.out.println(getTORestsList(RestaurantTestData.rests, convertDishListInDishToList(DishTestData.dishList)));

        System.out.println(getFilteredTOsForAdmin(RestaurantTestData.rests, DishTestData.dishList, false));

//        System.out.println(getFilteredRatingRestForUser(RestaurantTestData.rests, convertDishListInDishToList(DishTestData.dishList), 100, 145));

    }

    @Test
    public static List<RestaurantTo> getTORestsList(List<Restaurant> restaurants, List<DishTo> dishes) {
        return RestaurantsAndDishesUtil.getTORestsList(restaurants, dishes);
    }


    @Test
    public static List<RestaurantTo> getFilteredTOsForAdmin(List<Restaurant> restaurants, List<Dish> dishes, boolean filter) {
        return RestaurantsAndDishesUtil.getFilteredTOsForAdmin(restaurants, dishes, filter);
    }

    @Test
    public static List<DishTo> convertDishListInDishToList(List<Dish> dishes) {
        return RestaurantsAndDishesUtil.convertDishListInDishToList(dishes);
    }

//    @Test
//    public static List<RestaurantTo> getFilteredRatingRestForUser(List<Restaurant> rest, List<DishTo> dishes, int start, int end) {
//        return RestaurantsAndDishesUtil.getFilteredRatingRestForUser(rest, dishes, start, end);
//    }

}
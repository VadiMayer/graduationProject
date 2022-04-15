package topjava.quest.util;

import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.to.DishTo;
import topjava.quest.to.RestaurantTo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsUtil {

    public static void main(String[] args) {

        RestaurantTo restDish = new RestaurantTo(1, "White", 200);

        RestaurantTo.Menu menu = restDish.new Menu(new ArrayList<>());

        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 4, 14, 0, 0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2022, 4, 12, 23, 59);

        List<Dish> dishList = List.of
                (
                        new Dish(1, "Первое", 200, LocalDateTime.now()),
                        new Dish(2, "Второе", 400, LocalDateTime.now()),
                        new Dish(3, "Третье", 100, localDateTime1),
                        new Dish(4, "Четвертое", 50, localDateTime2)
                );

        List<Restaurant> restaurantList = List.of
                (
                        new Restaurant()
                );

        System.out.println(getTOList(restaurantList, convertListDishTo(dishList)));

    }


    public static List<DishTo> convertListDishTo(List<Dish> dishes) {

        List<Dish> currentDate = dishes
                .stream()
                .filter(date -> date.getUpdateDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())).toList();

        LocalDate onlyCurrentDate = currentDate.get(1).getUpdateDate().toLocalDate();

        return dishes.stream()
                .map(dish -> createTo(dish, dish.getUpdateDate().toLocalDate().toString().equals(onlyCurrentDate.toString()))).toList();
    }

    private static DishTo createTo(Dish dish, boolean requiresAnUpdate) {
        return new DishTo(dish.getId(), dish.getName(), dish.getCost(), dish.getUpdateDate(), requiresAnUpdate);
    }

    public static List<RestaurantTo> getTOList(List<Restaurant> restaurants, List<DishTo> dishes) {
        return null;
    }

    public static List<RestaurantTo> filterByPredicate() {
        return null;
    }
}

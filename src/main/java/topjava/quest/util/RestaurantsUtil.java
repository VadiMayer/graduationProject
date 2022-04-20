package topjava.quest.util;

import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.to.DishTo;
import topjava.quest.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsUtil {

    static DishTo createDishTo(Dish dish, boolean notRequiresAnUpdate) {
        return new DishTo(dish.getId(), dish.getName(), dish.getCost(), dish.getRestaurantId(), dish.getUpdateDate(), notRequiresAnUpdate);
    }

    public static List<DishTo> convertDishListInDishToList(List<Dish> dishes) {
        return dishes.stream()
                .map(dish -> createDishTo(dish, !(dish.getUpdateDate().toLocalDate().toString().equals(LocalDateTime.now().toLocalDate().toString())))).toList();
    }

    //Получить список всех ресторанов с едой для view.
    public static List<RestaurantTo> getTORestsList(List<Restaurant> restaurants, List<DishTo> dishes) {

        List<RestaurantTo> restaurantsTo = restaurants.stream()
                .map(el -> new RestaurantTo(el.getId(), el.getName(), el.getRating(), el.getRestaurant_id())).toList();

        List<RestaurantTo> restaurantToFalseAndTrue = new ArrayList<>();

        //Если ресторан (true) то, он горит красным, если (false) то зеленым
        for (RestaurantTo restaurant:restaurantsTo) {
            List<DishTo> dishToRestaurantList = dishes.stream().filter(e -> e.getRestaurantId() == restaurant.getRestaurant_id()).toList();
            for (DishTo dish:dishToRestaurantList) {
                if (dish.isError()) {
                    restaurant.setError(true);
                    break;
                }
            }
            restaurantToFalseAndTrue.add(restaurant);
        }

        return restaurantToFalseAndTrue;
    }

    //For ADMIN
    public static List<RestaurantTo> getFilteredTOsForAdmin(List<Restaurant> restaurants, List<Dish> dishes, boolean filter) {
        List<RestaurantTo> allRestaurantTrueAndFalse = getTORestsList(restaurants, convertDishListInDishToList(dishes));
        // если (true) требует фильтрации, цвет красный, иначе зеленый
        return allRestaurantTrueAndFalse.stream().filter(rest -> rest.isError() == filter).toList();
    }


    //For USER
    public static List<RestaurantTo> getFilteredRatingRestForUser(List<Restaurant> rest, List<DishTo> dishes, int start, int end) {
        return getTORestsList(rest,dishes).stream().filter(res -> res.getRating() >= start && res.getRating() <= end).toList();
    }


//    public static <T extends Comparable<T>> boolean isBetweenTwoGaps(T value, T start, T end) {
//        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
//    }

}

package topjava.quest.util;

import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.to.DishTo;
import topjava.quest.to.RestaurantTo;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class RestaurantsAndDishesUtil {

    static Dish createDishFromDishTo(DishTo dishTo) {
        return new Dish();
    }

    static DishTo createDishTo(Dish dish, boolean notRequiresAnUpdate) {
        return new DishTo(dish.getId(), dish.getDescription(), dish.getCost(), dish.getRestaurant().getRestaurant_id(), dish.getUpdateDate(), notRequiresAnUpdate);
    }

    public static List<DishTo> convertDishListInDishToList(List<Dish> dishes) {
        return dishes.stream()
                .map(dish -> createDishTo(dish, !(dish.getUpdateDate().toLocalDate().toString().equals(LocalDateTime.now().toLocalDate().toString()))))
                .sorted().toList();
    }

    //Получить список всех ресторанов с едой для view.
    public static List<RestaurantTo> getTORestsList(List<Restaurant> restaurants, List<DishTo> dishes) {

        List<DishTo> withoutDuplicatesByFieldRestaurant_Id = dishes.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(DishTo::getRestaurantId))), ArrayList::new));
        Map<Integer, List<DishTo>> dishToMap = new HashMap<>();
        int count = 0;

        for (int i = 0; i < withoutDuplicatesByFieldRestaurant_Id.size(); i++) {
            if (dishToMap.size() == 0) {
                List<DishTo> temp = new ArrayList<>();
                temp.add(withoutDuplicatesByFieldRestaurant_Id.get(i));
                dishToMap.put(temp.get(i).getRestaurantId(), temp);
                i = -1;
                count = count + 1;
            } else
                for (int j = 1; j < dishes.size(); j++) {
                    if (dishToMap.get(withoutDuplicatesByFieldRestaurant_Id.get(i).getRestaurantId()) != null
                            && withoutDuplicatesByFieldRestaurant_Id.get(i).getRestaurantId() == dishes.get(count).getRestaurantId()) {
                        dishToMap.get(dishes.get(count).getRestaurantId()).add(dishes.get(count));
                        count = count + 1;
                        if (count == dishes.size()) {
                            break;
                        }
                    } else {
                        List<DishTo> temp = new ArrayList<>();
                        temp.add(withoutDuplicatesByFieldRestaurant_Id.get(i + 1));
                        dishToMap.put(temp.get(0).getRestaurantId(), temp);
                        count = count + 1;
                        break;
                    }
                }
        }

        List<RestaurantTo> restaurantsTo = restaurants.stream()
                .map(el -> new RestaurantTo(el.getId(), el.getName(), el.getRestaurant_id(), dishToMap.get(el.getRestaurant_id())))
                .toList();

        List<RestaurantTo> restaurantToFalseAndTrue = new ArrayList<>();

        //Если ресторан (true) то, он горит красным, если (false) то зеленым
        for (RestaurantTo restaurant : restaurantsTo) {
            List<DishTo> dishToRestaurantList = dishes.stream().filter(e -> e.getRestaurantId() == restaurant.getRestaurant_id()).toList();
            for (DishTo dish : dishToRestaurantList) {
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
        // если (true) требует фильтрации - цвет красный, иначе зеленый
        return allRestaurantTrueAndFalse.stream().filter(rest -> rest.isError() == filter).toList();
    }


//    //For USER
//    public static List<RestaurantTo> getFilteredRatingRestForUser(List<Restaurant> rest, List<DishTo> dishes, int start, int end) {
//        return getTORestsList(rest, dishes).stream().filter(res -> res.getRating() >= start && res.getRating() <= end).toList();
//    }


//    public static <T extends Comparable<T>> boolean isBetweenTwoGaps(T value, T start, T end) {
//        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
//    }

}

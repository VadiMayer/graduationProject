package topjava.quest.repository;

import topjava.quest.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    List<Dish> getAllDishesForRestaurant(int restaurant_id);

    List<Dish> getRequiresAnUpdate(LocalDateTime start, LocalDateTime end);
}

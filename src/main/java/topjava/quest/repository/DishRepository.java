package topjava.quest.repository;

import topjava.quest.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id);

    List<Dish> getAllDishes();

    List<Dish> getRequiresAnUpdate(LocalDateTime start, LocalDateTime end);
}

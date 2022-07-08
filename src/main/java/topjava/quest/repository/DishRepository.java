package topjava.quest.repository;

import topjava.quest.model.Dish;

import java.time.LocalDate;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id);

    List<Dish> getAllDishes();

    List<Dish> getRequiresAnUpdate(LocalDate start, LocalDate end);
}

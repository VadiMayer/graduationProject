package topjava.quest.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;
import topjava.quest.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    public void delete(int id, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Dish> getAll() {
        return repository.getAllDishes();
    }

    public void getRequiresAnUpdate(LocalDateTime localDateTime) {

    }
}

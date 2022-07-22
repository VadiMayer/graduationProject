package topjava.quest.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;
import topjava.quest.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish create(Dish dish, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(dish, restaurantId);
        return repository.save(dish, restaurantId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void update(Dish dish, int restaurantId) {
        ValidationUtil.checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Cacheable(value = "dishes")
    public List<Dish> getAll() {
        return repository.getAllDishes();
    }
}

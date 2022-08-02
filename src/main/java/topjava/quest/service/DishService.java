package topjava.quest.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.DishRepository;
import topjava.quest.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish create(Dish dish, int restaurantId) {
        Objects.requireNonNull(dish, "dish mustn't be null");
        return repository.save(dish, restaurantId);
    }

    @Transactional
    @CacheEvict(value = "dishes", allEntries = true)
    public void update(Dish dish) {
        Dish dishCheck = repository.get(dish.getId());
        dishCheck.setDescription(dish.getDescription());
        dishCheck.setCost(dish.getCost());
        dishCheck.setUpdateDate(LocalDate.now());
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

package topjava.quest.service;

import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish) {
        return null;
    }

    public void update() {

    }

    public void delete(int id) {

    }

    public List<Dish> getAll() {
        return null;
    }

    public void getRequiresAnUpdate(LocalDateTime localDateTime) {

    }
}

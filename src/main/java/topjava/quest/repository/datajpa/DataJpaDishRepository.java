package topjava.quest.repository.datajpa;

import org.springframework.stereotype.Repository;
import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudDishRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository) {
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    public Dish save(Dish dish) {
        if (!dish.isNew()) {
            return null;
        }
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public List<Dish> getAllDishes() {
        return crudDishRepository.getAll();
    }

    @Override
    public List<Dish> getFilteredTOsForAdmin(LocalDateTime start, LocalDateTime end) {
        return null;
    }
}

package topjava.quest.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository) {
        this.crudRepository = crudDishRepository;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew()) {
            return null;
        }
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public List<Dish> getAllDishes() {
        return crudRepository.getAll();
    }

}

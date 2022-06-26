package topjava.quest.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
//    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew()) {
            return null;
        }
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }

    @Override
    public List<Restaurant> getBetweenRating(int startRating, int endRating) {
        return crudRestaurantRepository.getBetweenRating(startRating, endRating);
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return crudRestaurantRepository.getWithDishes(id);
    }
}

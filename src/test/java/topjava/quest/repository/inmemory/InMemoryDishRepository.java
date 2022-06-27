package topjava.quest.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import topjava.quest.DishTestData;
import topjava.quest.RestaurantTestData;
import topjava.quest.model.Dish;
import topjava.quest.repository.DishRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDishRepository implements DishRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryDishRepository.class);

    // Map restaurant_id : dishRepository
    private final Map<Integer, InMemoryBaseRepository<Dish>> restaurantsDishesMap = new ConcurrentHashMap<>();

    {
        var restaurantDishes = new InMemoryBaseRepository<Dish>();
        DishTestData.dishList.forEach(restaurantDishes::put);
        restaurantsDishesMap.put(RestaurantTestData.rests.get(0).getRestaurant_id(), restaurantDishes);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        Objects.requireNonNull(dish, "dish must not be null");
        var dishes = restaurantsDishesMap.computeIfAbsent(restaurantId, rId -> new InMemoryBaseRepository<>());
        return dishes.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public List<Dish> getAllDishesForRestaurant(int restaurant_id) {
        return null;
    }

    @Override
    public List<Dish> getRequiresAnUpdate(LocalDateTime start, LocalDateTime end) {
        return null;
    }
}

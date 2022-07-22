package topjava.quest.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.RestaurantRepository;
import topjava.quest.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant) {
        Restaurant restaurantCheck = ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurant.getId()), restaurant.getId());
        restaurantCheck.setName(restaurant.getName());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public Restaurant getWithDishes(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.getWithDishes(id), id);
    }
}

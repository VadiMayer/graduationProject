package topjava.quest.service;

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

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    //Без аннотации транзакции не сохранялось имя ресторана!!!
    //Не проходил update даже если был restaurantRepository.save(restaurantCheck);
    @Transactional
    public void update(Restaurant restaurant) {
        Restaurant restaurantCheck = ValidationUtil.checkNotFoundWithId(restaurantRepository.get(restaurant.getId()), restaurant.getId());
        restaurantCheck.setName(restaurant.getName());
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public Restaurant getWithDishes(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.getWithDishes(id), id);
    }
}

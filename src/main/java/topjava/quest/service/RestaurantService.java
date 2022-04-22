package topjava.quest.service;

import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.DishRepository;
import topjava.quest.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {

    private final DishRepository repositoryDish;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(DishRepository repositoryDish, RestaurantRepository restaurantRepository) {
        this.repositoryDish = repositoryDish;
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    public void update(Restaurant restaurant) {

    }

    public void delete(int id) {

    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }

    public List<Dish> getAllDishes() {
        return repositoryDish.getAllDishes();
    }

}

package topjava.quest.service;

import topjava.quest.model.Restaurant;
import topjava.quest.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    public void update(Restaurant restaurant) {

    }

    public void delete(int id) {

    }

    public List<Restaurant> getAll() {
        return null;
    }

}

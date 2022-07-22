package topjava.quest.repository;

import topjava.quest.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAllRestaurants();

    default Restaurant getWithDishes(int id) {
        throw new UnsupportedOperationException();
    }
}

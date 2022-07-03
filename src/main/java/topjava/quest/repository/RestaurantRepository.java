package topjava.quest.repository;

import topjava.quest.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAllRestaurants();

//    List<Restaurant> getBetweenRating(int startRating, int endRating);

    default Restaurant getWithDishes(int id) {
        throw new UnsupportedOperationException();
    }
}

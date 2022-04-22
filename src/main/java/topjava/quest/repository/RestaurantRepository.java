package topjava.quest.repository;

import topjava.quest.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getBetweenRating(int startRating, int endRating);

}

package topjava.quest.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import topjava.quest.model.Restaurant;

import java.util.List;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantRepository.class);

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return null;
    }

    @Override
    public List<Restaurant> getBetweenRating(int startRating, int endRating) {
        return null;
    }

}

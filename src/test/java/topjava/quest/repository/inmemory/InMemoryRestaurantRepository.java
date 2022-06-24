package topjava.quest.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import topjava.quest.model.Restaurant;
import topjava.quest.repository.RestaurantRepository;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

import static topjava.quest.RestaurantTestData.rests;

@Repository
public class InMemoryRestaurantRepository extends InMemoryBaseRepository<Restaurant> implements RestaurantRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantRepository.class);

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    public void init() {
        map.clear();
        for (Restaurant rest : rests) {
            put(rest);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return getCollection().stream()
                .sorted(Comparator.comparing(Restaurant::getRating).thenComparing(Restaurant::getName))
                .toList();
    }

    @Override
    public List<Restaurant> getBetweenRating(int startRating, int endRating) {
        return null;
    }

}

package topjava.quest.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topjava.quest.model.Restaurant;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.RestaurantTo;

import java.util.List;

import static topjava.quest.util.ValidationUtil.assureIdConsistent;
import static topjava.quest.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService restaurantService;

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return null;
    }
}

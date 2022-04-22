package topjava.quest.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.RestaurantTo;
import topjava.quest.util.RestaurantsUtil;
import topjava.quest.util.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static topjava.quest.util.ValidationUtil.assureIdConsistent;
import static topjava.quest.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    // Получаем юзера, нужно узнать он Админ или нет,
    // если Админ у него есть возможность удалять или добавлять ресторан,
    // если Пользователь может голосовать!

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public List<RestaurantTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll");
        return RestaurantsUtil.getTORestsList(restaurantService.getAllRestaurants(),
                RestaurantsUtil.convertDishListInDishToList(restaurantService.getAllDishes()));
    }

    public List<RestaurantTo> getBetween(@Nullable LocalDate start, @Nullable LocalDate end, boolean restaurantNeedUpdate) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) for user {}", start, end, userId);
        List<Dish> dishDateFiltered = null;
        List<Restaurant> restaurantsDateFiltered = null;
        return RestaurantsUtil.getFilteredTOsForAdmin(restaurantsDateFiltered, dishDateFiltered, restaurantNeedUpdate);
    }
}

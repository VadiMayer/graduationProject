package topjava.quest.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.RestaurantTo;
import topjava.quest.util.RestaurantsAndDishesUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static topjava.quest.util.RestaurantsAndDishesUtil.convertDishListInDishToList;
import static topjava.quest.util.ValidationUtil.assureIdConsistent;
import static topjava.quest.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "/users/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService restaurantService;

    private final DishService dishService;

    public RestaurantUIController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return RestaurantsAndDishesUtil.getTORestsList(restaurantService.getAllRestaurants(),
                convertDishListInDishToList(dishService.getAll()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        //        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    @PostMapping
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant) {
//        int userId = SecurityUtil.authUserId();
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    @PutMapping
    public void updateRestaurant(@RequestBody Restaurant restaurant) {
//        int userId = SecurityUtil.authUserId();
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, restaurant.getId());
        restaurantService.update(restaurant);
    }

//    public List<RestaurantTo> getBetweenRating(int startRating, int endRating) {
//        List<RestaurantTo> toRestsList = RestaurantsAndDishesUtil.getTORestsList(restaurantService.getAllRestaurants(), convertDishListInDishToList(dishService.getAll()));
//        return toRestsList.stream()
//                .filter(rest -> rest.getRating() > startRating && rest.getRating() < endRating).toList();
//    }

    //Сортировка для админа по дате обновления еды
    public List<RestaurantTo> getBetween(@Nullable LocalDate start, @Nullable LocalDate end, boolean restaurantNeedUpdate) {
//        int userId = SecurityUtil.authUserId();
//        log.info("getBetween dates({} - {}) for user {}", start, end, userId);
        List<Dish> dishDateFiltered = null;
        List<Restaurant> restaurantsDateFiltered = null;
        return RestaurantsAndDishesUtil.getFilteredTOsForAdmin(restaurantsDateFiltered, dishDateFiltered, restaurantNeedUpdate);
    }

    private int getRestaurantId(HttpServletRequest request) {
        return Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
    }

}

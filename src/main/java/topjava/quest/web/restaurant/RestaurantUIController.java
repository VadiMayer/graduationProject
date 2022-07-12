package topjava.quest.web.restaurant;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.RestaurantTo;
import topjava.quest.util.RestaurantsAndDishesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static topjava.quest.util.RestaurantsAndDishesUtil.convertDishListInDishToList;
import static topjava.quest.util.ValidationUtil.assureIdConsistent;
import static topjava.quest.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantUIController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/users/restaurants";

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
    public List<RestaurantTo> getAllWithMenu() {
        log.info("getAll");
        //при аннотации @JsonIgnore на поле private List<Dish> menu выводит все рестораны.
        return RestaurantsAndDishesUtil.getTORestsList(restaurantService.getAllRestaurants(),
                convertDishListInDishToList(dishService.getAll()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "Restaurant_Id", example = "100006") @PathVariable int id) {
        //        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody @Valid Restaurant restaurant) {
//        int userId = SecurityUtil.authUserId();
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant newRestaurant = restaurantService.create(restaurant);

        URI uriNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();

        return ResponseEntity.created(uriNewResource).body(newRestaurant);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurant(@PathVariable(name = "id") @ApiParam(value = "Restaurant_Id", example = "100006") int id,
                                 @RequestBody @Valid Restaurant restaurant) {
//        int userId = SecurityUtil.authUserId();
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
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

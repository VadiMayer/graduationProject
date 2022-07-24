package topjava.quest.web.restaurant;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.quest.model.Dish;
import topjava.quest.model.Restaurant;
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.RestaurantTo;
import topjava.quest.util.Util;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static topjava.quest.util.Util.convertDishListInDishToList;
import static topjava.quest.util.ValidationUtil.assureIdConsistent;
import static topjava.quest.util.ValidationUtil.checkNew;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_ADMIN_URL = "/users/admin/restaurants";

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

    @ApiOperation(value = "Get all restaurants with menu",
            notes = "For users and admins",
            authorizations = {@Authorization(value = "Basic")})
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/users/restaurants")
    public List<RestaurantTo> getAllWithMenu() {
        log.info("getAll");
        return Util.getTORestsList(restaurantService.getAllRestaurants(),
                convertDishListInDishToList(dishService.getAll()));
    }

    @ApiOperation(value = "Delete restaurant",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @DeleteMapping(REST_ADMIN_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(value = "Restaurant_Id", example = "100006") @PathVariable int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    @ApiOperation(value = "Create a new restaurant",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @PostMapping(REST_ADMIN_URL)
    public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody @Valid Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant newRestaurant = restaurantService.create(restaurant);

        URI uriNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_ADMIN_URL + "/{id}")
                .buildAndExpand(newRestaurant.getId()).toUri();

        return ResponseEntity.created(uriNewResource).body(newRestaurant);
    }

    @ApiOperation(value = "Update a restaurant",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @PutMapping(REST_ADMIN_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRestaurant(@PathVariable(name = "id") @ApiParam(value = "Restaurant_Id", example = "100006") int id,
                                 @RequestBody @Valid Restaurant restaurant) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        restaurantService.update(restaurant);
    }

    @ApiOperation(value = "Filter the restaurants",
            notes = "Only for admins. Filter restaurants, if need requiring update or not.",
            authorizations = {@Authorization(value = "Basic")})
    @GetMapping(REST_ADMIN_URL + "/filter")
    public List<RestaurantTo> getBetweenRequiringUpdating(boolean restaurantRequiringUpdating) {
        log.info("get between requiring updating {}", restaurantRequiringUpdating);
        List<Dish> dishesList = dishService.getAll();
        List<Restaurant> restaurantsList = restaurantService.getAllRestaurants();
        return Util.getFilteredTOsForAdmin(restaurantsList, dishesList, restaurantRequiringUpdating);
    }

}

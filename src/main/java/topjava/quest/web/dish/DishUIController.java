package topjava.quest.web.dish;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.quest.model.Dish;
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.DishTo;
import topjava.quest.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static topjava.quest.util.RestaurantsAndDishesUtil.convertDishListInDishToList;

@RestController
@RequestMapping(value = DishUIController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishUIController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/users/dishes";

    private final RestaurantService restaurantService;

    private final DishService dishService;

    public DishUIController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping
    public List<DishTo> getAll() {
        log.info("getAll");
        return convertDishListInDishToList(dishService.getAll());
    }

    @ApiOperation(value = "Create a dish for the restaurant")
    @PostMapping(value = "restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishForRestaurant(@RequestBody @Valid DishTo dishTo) {
        log.info("create {} for restaurant {}", dishTo, dishTo.getRestaurantId());

        Dish dish = new Dish(dishTo.getId(), dishTo.getName(), dishTo.getCost(), dishTo.getUpdateDate(), restaurantService.get(dishTo.getRestaurantId()));
        ValidationUtil.checkNew(dish);
        dishService.create(dish, dishTo.getRestaurantId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(dish.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(dish);
    }

    @PostMapping("{id}")
    public void delete(@PathVariable(name = "id") int id) {
        log.info("delete dish {}", id);
        dishService.delete(id);
    }
}

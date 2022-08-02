package topjava.quest.web.dish;

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
import topjava.quest.service.DishService;
import topjava.quest.service.RestaurantService;
import topjava.quest.to.DishTo;
import topjava.quest.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static topjava.quest.util.Util.convertDishListInDishToList;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/users/admin/dishes";

    private final RestaurantService restaurantService;

    private final DishService dishService;

    public DishRestController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @ApiOperation(value = "Get all dishes",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @GetMapping
    public List<DishTo> getAll() {
        log.info("getAll");
        return convertDishListInDishToList(dishService.getAll());
    }

    @ApiOperation(value = "Create a dish for the restaurant",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @PostMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> createDishForRestaurant(@PathVariable(name = "id")
                                                        @ApiParam(name = "id", value = "Restaurant ID", example = "100006") int restaurant_Id,
                                                        @Valid @RequestBody DishTo dishTo) {
        log.info("create {} for restaurant {}", dishTo, restaurant_Id);

        ValidationUtil.checkNew(dishTo);
        Dish dishWithDate = new Dish(dishTo.getId(), dishTo.getName(), dishTo.getCost(), LocalDate.now(), restaurantService.get(restaurant_Id));

        dishService.create(dishWithDate, restaurant_Id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(dishTo.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(dishWithDate);
    }

    @ApiOperation(value = "Update a dish",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable(name = "id") @ApiParam(name = "id", value = "Dish ID", example = "100012") int dish_Id,
                       @Valid @RequestBody DishTo dishTo) {
        log.info("update dish with id {}", dish_Id);
        Dish dishWithDate = new Dish(dish_Id, dishTo.getName(), dishTo.getCost(), LocalDate.now());
        dishService.update(dishWithDate);
    }

    @ApiOperation(value = "Delete dish",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id")
                       @ApiParam(name = "id", value = "Dish id", example = "100012") int id) {
        log.info("delete dish {}", id);
        dishService.delete(id);
    }
}

package topjava.quest.web.restaurant;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import topjava.quest.model.Restaurant;
import topjava.quest.to.RestaurantTo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@ApiIgnore
@RestController
@RequestMapping("/restaurants")
public class RestaurantUIController extends AbstractRestaurantController {

    @Override
    @GetMapping
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate(Restaurant restaurant) {
        if (restaurant.isNew()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, restaurant.getId());
        }
    }

    @GetMapping("/filter")
    public List<RestaurantTo> getBetweenRating(@RequestParam @Nullable int startRating,
                                               @RequestParam @Nullable int endRating) {
        return super.getBetweenRating(startRating, endRating);
    }

    private int getRestaurantId(HttpServletRequest request) {
        return Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
    }

}

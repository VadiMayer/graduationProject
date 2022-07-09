package topjava.quest.web.vote;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import topjava.quest.model.Restaurant;
import topjava.quest.model.User;
import topjava.quest.model.Vote;
import topjava.quest.service.RestaurantService;
import topjava.quest.service.UserService;
import topjava.quest.service.VoteService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static topjava.quest.util.RestaurantsAndDishesUtil.getVote;

@RestController
@RequestMapping(value = VoteUIController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteUIController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/users/votes";

    private final VoteService voteService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public VoteUIController(VoteService voteService, RestaurantService restaurantService, UserService userService) {
        this.voteService = voteService;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping("/restaurants/{id}")
    public List<Vote> getWithRestaurant(@ApiParam(name = "id", value = "Restaurants_Id", example = "100005") @PathVariable(name = "id") int restaurantsId) {
        log.info("get votes for restaurant with id {}", restaurantsId);
        List<Vote> votesList = voteService.getAllForRestaurant(restaurantsId);
        return votesList;
    }
    @PostMapping("/restaurants/{id}")
    public Vote createOrUpdateVote(@PathVariable(name = "id") int restaurantsId) {
        log.info("Vote for restaurant with id {} by user", restaurantsId);
        Vote created;
        User user = userService.get(100001);
        Restaurant restaurant = restaurantService.get(restaurantsId);
        Vote newVote = new Vote(null, user, restaurant, LocalDate.now());

        if (voteService.getForToday(100001) != null) {
            newVote.setId(voteService.getForToday(100001).getId());
            created = getVote(voteService.update(newVote, LocalTime.now()));
        } else {
            created = getVote(voteService.create(newVote));
        }

        return created;
    }

}

package topjava.quest.web.vote;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import topjava.quest.AuthorizedUser;
import topjava.quest.model.Restaurant;
import topjava.quest.model.User;
import topjava.quest.model.Vote;
import topjava.quest.service.RestaurantService;
import topjava.quest.service.UserService;
import topjava.quest.service.VoteService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static topjava.quest.util.Util.getVote;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
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

    @ApiOperation(value = "Get restaurant with votes",
            notes = "For users and admins.",
            authorizations = {@Authorization(value = "Basic")})
    @GetMapping("/restaurants/{id}")
    public List<Vote> getWithRestaurant(@ApiParam(name = "id", value = "Restaurants_Id", example = "100005") @PathVariable(name = "id") int restaurantsId) {
        log.info("get votes for restaurant with id {}", restaurantsId);
        return voteService.getAllForRestaurant(restaurantsId);
    }

    @ApiOperation(value = "Create or update your vote",
            notes = "For users and admins. If your vote until 11 a.m., you can edit, otherwise you must voting tomorrow.",
            authorizations = {@Authorization(value = "Basic")})
    @PostMapping("/restaurants/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> createOrUpdateVote(@PathVariable(name = "id")
                                                   @ApiParam(name = "id", value = "Restaurant id", example = "100005") int restaurantsId,
                                                   @ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("Vote for restaurant with id {} by user", restaurantsId);
        Vote created;
        User user = userService.get(authorizedUser.getId());
        Restaurant restaurant = restaurantService.get(restaurantsId);
        Vote newVote = new Vote(null, user, restaurant, LocalDate.now());

        if (voteService.getForToday(authorizedUser.getId()) != null) {
            newVote.setId(voteService.getForToday(authorizedUser.getId()).getId());
            created = getVote(voteService.update(newVote, LocalTime.now()));
        } else {
            created = getVote(voteService.create(newVote));
        }

        URI uriNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/restaurants/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriNewResource).body(created);
    }

}

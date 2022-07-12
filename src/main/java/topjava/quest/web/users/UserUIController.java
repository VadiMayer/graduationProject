package topjava.quest.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.quest.model.User;
import topjava.quest.service.UserService;
import topjava.quest.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserUIController {
    private static final Logger log = LoggerFactory.getLogger(UserUIController.class);

    public final String REST_URL = "/users";

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user) {
        log.info("register {}", user);
        ValidationUtil.checkNew(user);

        URI uriNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + user.getId())
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uriNewResource).body(userService.create(user));
    }

}

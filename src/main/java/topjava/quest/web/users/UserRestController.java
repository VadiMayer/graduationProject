package topjava.quest.web.users;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.quest.model.User;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractRestController {

    public final String REST_URL = "/users";

    @ApiOperation(value = "Get all users",
            notes = "Only for admins",
            authorizations = {@Authorization(value = "Basic")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public List<User> getAllUsers() {
        return super.getAllUsers();
    }

    @ApiOperation(value = "Register a new user",
            notes = "This endpoint for unregistered users.")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user) {

        URI uriNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + user.getId())
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uriNewResource).body(super.create(user));
    }

}

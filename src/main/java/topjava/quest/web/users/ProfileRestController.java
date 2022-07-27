package topjava.quest.web.users;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import topjava.quest.AuthorizedUser;
import topjava.quest.to.UserTo;

import javax.validation.Valid;

import java.util.Objects;

import static topjava.quest.web.users.ProfileRestController.REST_URL;

@RestController
@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractRestController {

    public static final String REST_URL = "/users/profile";

    @ApiOperation(value = "Update your profile",
            notes = "For users and admins.",
            authorizations = {@Authorization(value = "Basic")})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo,
                       @ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.update(userTo, authorizedUser.getId());
    }

    @ApiOperation(value = "Delete your profile",
            notes = "For users and admins.",
            authorizations = {@Authorization(value = "Basic")})
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiIgnore @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.delete(authorizedUser.getId());
    }
}

package topjava.quest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import topjava.quest.model.Role;
import topjava.quest.model.User;
import topjava.quest.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static topjava.quest.data.UserTestData.*;

class DataJpaUserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @Test
    void create() {
        User created = userService.create(getNewUser());
        Integer newId = created.getId();
        User newUser = getNewUser();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void getAllUsers() {
        USER_MATCHER.assertMatch(userService.getAllUsers(), guest1, user1, user2, admin, guest2);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class,
                () -> userService.create(new User(null, "Duplicate", "vadim@gmail.com", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        userService.delete(ADMIN_ID);
        assertThrows(NotFoundException.class, () -> userService.get(ADMIN_ID));
    }
}
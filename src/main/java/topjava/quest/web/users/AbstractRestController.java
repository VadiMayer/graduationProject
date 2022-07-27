package topjava.quest.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.User;
import topjava.quest.service.UserService;
import topjava.quest.to.UserTo;
import topjava.quest.util.ValidationUtil;

import java.util.List;

import static topjava.quest.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractRestController {

    protected final Logger log = LoggerFactory.getLogger(AbstractRestController.class);

    @Autowired
    UserService userService;

    public List<User> getAllUsers() {
        log.info("get all users");
        return userService.getAllUsers();
    }

    public User create(User user) {
        log.info("register user {}", user);
        ValidationUtil.checkNew(user);
        return userService.create(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update user {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    public void delete(int id) {
        log.info("delete user with id {}", id);
        userService.delete(id);
    }
}

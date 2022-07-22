package topjava.quest;

import topjava.quest.model.User;
import topjava.quest.to.UserTo;
import topjava.quest.util.UtilForTo;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoleSet());
        this.userTo = UtilForTo.userAsTo(user);
    }

    public int getId() {
        return userTo.id();
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}

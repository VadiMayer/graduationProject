package topjava.quest;

import topjava.quest.model.User;
import topjava.quest.to.UserTo;
import topjava.quest.util.Util;

import java.io.Serial;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoleSet());
        setUserTo(Util.userAsTo(user));
    }

    public int getId() {
        return userTo.id();
    }

    public void setUserTo(UserTo userTo) {
        userTo.setPassword(null);
        this.userTo = userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}

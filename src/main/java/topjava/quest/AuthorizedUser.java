package topjava.quest;

import topjava.quest.model.User;

public class AuthorizedUser {
    private static final long serialVersionUID = 1L;

    private transient User user;

    public AuthorizedUser(User user) {

    }

    public int getId() {
        return user.id();
    }

    public void setUser(User user) {

    }

    public User getUser() {
        return user;
    }
}

package topjava.quest;

import topjava.quest.model.Role;
import topjava.quest.model.User;

import java.util.Collections;

import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ;
    public static final int GUEST_ID = START_SEQ;
    public static final int NOT_FOUND = 10;

    public static final User admin = new User(ADMIN_ID, "VadimUserAdmin", "vadim@gmail.com", "vadim15", Role.USER, Role.ADMIN);
    public static final User user1 = new User(USER_ID + 1, "MuratUser", "murat@gmail.com", "murat60", Role.USER);
    public static final User user2 = new User(USER_ID + 2, "SergeyUser", "sergey@gmail.com", "sergey", Role.USER);
    public static final User guest1 = new User(GUEST_ID + 3, "KatyGuest", "guest1@gmail.com", "katy5");
    public static final User guest2 = new User(GUEST_ID + 4, "VikaGuest", "guest2@gmail.com", "vika6");

    public static User getNewUser() {
        return new User(null, "NewUser", "newUser@gmail.com", "newUserPass", Role.USER);
    }

    public static User getUpdatedUser() {
        User modified = new User(admin);
        modified.setEmail("modified@gmail.com");
        modified.setName("modifiedName");
        modified.setPassword("modifiedPass");
        modified.setRoleSet(Collections.singleton(Role.ADMIN));
        return modified;
    }
}

package topjava.quest.repository;

import topjava.quest.model.User;

public interface UserRepository {

    User save(User user);

    User get(int id);
}

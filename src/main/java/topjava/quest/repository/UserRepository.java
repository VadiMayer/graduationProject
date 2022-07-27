package topjava.quest.repository;

import topjava.quest.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(int id);

    List<User> getAllUsers();

    User getByEmail(String email);

    boolean delete(int id);

}

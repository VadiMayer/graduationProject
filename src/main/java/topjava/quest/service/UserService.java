package topjava.quest.service;

import org.springframework.stereotype.Service;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;
import topjava.quest.util.ValidationUtil;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public User create(User user) {
        Objects.requireNonNull(user, "User must be not null");
        return repository.save(user);
    }
}

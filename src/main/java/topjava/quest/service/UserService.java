package topjava.quest.service;

import org.springframework.stereotype.Service;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;
import topjava.quest.util.ValidationUtil;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }
}

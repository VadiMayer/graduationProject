package topjava.quest.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import topjava.quest.AuthorizedUser;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;
import topjava.quest.util.ValidationUtil;

import java.util.List;
import java.util.Objects;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Objects.requireNonNull(user, "User must be not null");
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " isn't found");
        }
        return new AuthorizedUser(user);
    }
}

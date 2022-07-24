package topjava.quest.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import topjava.quest.AuthorizedUser;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;
import topjava.quest.util.ValidationUtil;

import java.util.List;
import java.util.Objects;

import static topjava.quest.util.Util.prepareToSave;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
        return prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
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

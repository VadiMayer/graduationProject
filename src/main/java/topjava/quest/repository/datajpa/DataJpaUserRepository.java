package topjava.quest.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudUserRepository;

    private final Sort sortName = Sort.by(Sort.Direction.ASC, "name");

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return crudUserRepository.findAll(sortName);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }
}

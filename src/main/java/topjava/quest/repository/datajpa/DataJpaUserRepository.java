package topjava.quest.repository.datajpa;

import org.springframework.stereotype.Repository;
import topjava.quest.model.User;
import topjava.quest.repository.UserRepository;

@Repository
public class DataJpaUserRepository implements UserRepository {

   private final CrudUserRepository crudUserRepository;

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
}

package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
}

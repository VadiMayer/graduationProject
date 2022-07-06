package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import topjava.quest.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}

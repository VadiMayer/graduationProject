package topjava.quest.repository.datajpa;

import org.springframework.stereotype.Repository;
import topjava.quest.model.Vote;
import topjava.quest.repository.VoteRepository;

import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public Vote save(Vote vote) {
        if (!vote.isNew() && get(vote.getId(), vote.getUser().getId()) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getById(vote.getUser().getId()));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public Vote getWithRestaurant(int id, int restaurantId) {
        return VoteRepository.super.getWithRestaurant(id, restaurantId);
    }
}

package topjava.quest.repository;

import topjava.quest.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote get(int id, int userId);

    Vote save(Vote vote);

    boolean delete(int id, int userId);

    List<Vote> getAll();

    default Vote getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }
}

package topjava.quest.repository;

import topjava.quest.model.Vote;

import java.time.LocalDate;

import java.util.List;

public interface VoteRepository {

    Vote get(int id, int userId);

    Vote getForToday(int authUserId, LocalDate date);

    Vote save(Vote vote);

    boolean delete(int id, int userId);

    List<Vote> getAll();

    default List<Vote> getAllForRestaurant(int restaurantId) {
        throw new UnsupportedOperationException();
    }
}

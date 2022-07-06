package topjava.quest.service;

import org.springframework.stereotype.Service;
import topjava.quest.model.Vote;
import topjava.quest.repository.VoteRepository;
import topjava.quest.util.ValidationUtil;

import java.time.LocalTime;
import java.util.Objects;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        Objects.requireNonNull(vote, "Vote must be not null");
        return voteRepository.save(vote);
    }

    public Vote update(Vote vote, LocalTime rightNow) {
        Objects.requireNonNull(vote, "Vote must be not null");
        Objects.requireNonNull(rightNow, "Time must be not null");
        ValidationUtil.checkingTheTime(rightNow);
        return ValidationUtil.checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public void delete(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote getWithRestaurant(int id, int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.getWithRestaurant(id, restaurantId), id);
    }
}

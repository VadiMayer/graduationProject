package topjava.quest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Vote;
import topjava.quest.repository.VoteRepository;
import topjava.quest.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @Transactional
    public Vote update(Vote vote, LocalTime rightNow) {
        Objects.requireNonNull(vote, "Vote must be not null");
        Objects.requireNonNull(rightNow, "Time must be not null");
        ValidationUtil.checkingTheTime(rightNow);
        return ValidationUtil.checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public void delete(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public List<Vote> getAllForRestaurant(int restaurantId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.getAllForRestaurant(restaurantId), restaurantId);
    }

    public Vote getForToday(int authUserId) {
        return voteRepository.getForToday(authUserId, LocalDate.now());
    }
}

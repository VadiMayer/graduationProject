package topjava.quest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import topjava.quest.model.Vote;
import topjava.quest.util.exception.NotFoundException;
import topjava.quest.util.exception.TimeIsUpException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static topjava.quest.data.UserTestData.*;
import static topjava.quest.data.RestaurantTestData.*;
import static topjava.quest.data.VoteTestData.*;

class DataJpaVoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService voteService;

    @Test
    void updateTimeExpired() {
        LocalTime currentTime = LocalTime.of(11, 1);
        assertThrows(TimeIsUpException.class, () -> voteService.update(VOTE_1, currentTime));
    }

    @Test
    void update() {
        LocalTime currentTime = LocalTime.of(10, 59);
        voteService.update(VOTE_2, currentTime);
    }

    @Test
    void create() {
        Vote created = voteService.create(new Vote(null, user1, RESTAURANT_100006, LocalDate.now()));
        Integer newId = created.getId();
        Vote newVote = new Vote(null, user1, RESTAURANT_100006, LocalDate.now());
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.get(newId, user1.id()), newVote);
    }

    @Test
    void delete() {
        voteService.delete(VOTE_1.getId(), admin.getId());
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE_2.getId(), admin.getId()));
    }

    @Test
    void getAllForRestaurant() {
        List<Vote> allVotes = voteService.getAllForRestaurant(RESTAURANT_100005.getRestaurant_id());
        VOTE_MATCHER.assertMatch(allVotes, VOTE_1, VOTE_4);
    }
}
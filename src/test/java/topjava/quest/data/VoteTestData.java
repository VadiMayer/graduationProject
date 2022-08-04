package topjava.quest.data;


import topjava.quest.MatcherFactory;
import topjava.quest.model.Vote;

import java.time.LocalDate;

import static topjava.quest.data.RestaurantTestData.*;
import static topjava.quest.data.UserTestData.*;
import static topjava.quest.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final Vote VOTE_1 = new Vote(START_SEQ + 19, admin, RESTAURANT_100005, LocalDate.now());
    public static final Vote VOTE_2 = new Vote(START_SEQ + 19, admin, RESTAURANT_100006, LocalDate.now());
    public static final Vote VOTE_3 = new Vote(START_SEQ + 23, user1, RESTAURANT_100007, LocalDate.now().minusDays(13));
    public static final Vote VOTE_4 = new Vote(START_SEQ + 21, user2, RESTAURANT_100005, LocalDate.of(2022,4,11));

}

package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import topjava.quest.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("DELETE FROM Vote v WHERE v.id=?1 AND v.user.id=?2")
    int delete(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.dateVote=:date")
    Vote getForToday(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id =:restaurantId")
    List<Vote> getAllForRestaurant(@Param("restaurantId") int restaurantId);

}

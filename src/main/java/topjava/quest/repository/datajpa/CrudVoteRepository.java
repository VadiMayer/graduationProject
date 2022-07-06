package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import topjava.quest.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("DELETE FROM Vote v WHERE v.id=?1 AND v.user.id=?2")
    int delete(int id, int userId);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id =:restaurantId")
    Vote getWithRestaurant(@Param("restaurantId") int restaurantId);

}

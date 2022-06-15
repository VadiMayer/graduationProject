package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Restaurant;

import java.util.List;

//@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

//    @Modifying
//    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.rating DESC")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.rating >= :startRating AND r.rating <= :endRating")
    List<Restaurant> getBetweenRating(@Param("startRating") int startRating, @Param("endRating") int endRating);

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getWithDishes(int id);
}

package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Restaurant;

import java.util.List;

//В этот класс аннотация @Repository не нужна, так как Spring-data-jpa понимает, во-первых, мы проксируем этот пакет,
// а во вторых то, что мы добавили в spring-db сканирование пакетов и Spring видит то, что extends от JpaRepository, а он в конечном итоге от Repository - маркерного интерфейса.
// @Transactional(readOnly = true) означает нам не нужно в сервисах маркировать эти транзакшинал и по умолчанию они все идут со скоупом readOnly, кроме тех которым мы явно помечаем.
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r ORDER BY r.rating DESC")
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.rating >= :startRating AND r.rating <= :endRating")
    List<Restaurant> getBetweenRating(@Param("startRating") int startRating, @Param("endRating") int endRating);

    //Проверить что это за аннотация + что нужно написать dishes или dish
    @EntityGraph(attributePaths = {"restaurant_dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(int id);
}

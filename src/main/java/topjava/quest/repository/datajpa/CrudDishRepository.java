package topjava.quest.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import topjava.quest.model.Dish;

import java.util.List;

//В этот класс аннотация @Repository не нужна, так как Spring-data-jpa понимает, во-первых, мы проксируем этот пакет,
// а во вторых то, что мы добавили в spring-db сканирование пакетов и Spring видит то, что extends от JpaRepository, а он в конечном итоге от Repository - маркерного интерфейса.
// @Transactional(readOnly = true) означает нам не нужно в сервисах маркировать эти транзакшинал и по умолчанию они все идут со скоупом readOnly, кроме тех которым мы явно помечаем.
@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    // операции, которые мы сами переопределяем и которые что-то модифицируют должны помечаться аннотацией @Modifying
    @Modifying
    @Transactional
    //@Query используются только при DATAJPA реализации ???
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    //@Query используются только при DATAJPA реализации ???
    @Query("SELECT d FROM Dish d ORDER BY d.updateDate DESC")
    List<Dish> getAll();
}

package topjava.quest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//@NamedQueries нужны только при JPA реализации ???
//@NamedQueries({
//        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.updateDate DESC"),
//        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
//        @NamedQuery(name = Dish.GET_BETWEEN_DATE,
//                query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.updateDate >= :startDate AND d.updateDate < :endDate ORDER BY d.updateDate DESC ")
//})
@Entity
@Table(name = "restaurant_dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "restaurant_id", name = "restaurant_dishes_unique_id_rest")})
public class Dish extends AbstractBaseEntity {
//    public static final String GET_ALL = "Dish.getAll";
//    public static final String DELETE = "Dish.delete";
//    public static final String GET_BETWEEN_DATE = "Dish.getBetween";

    //nullable в базе данных генерится колонка в таблице в которой мы не сможем создать null
    @Column(name = "description", nullable = false)
    //NotBlank говорит о том, что до того, как мы будем сохранять в базу данных проверяем поле,
    // что он не нулевое и не пустое. NotBlank относится только к String.
    @NotBlank
    @Size(min = 5, max = 90)
    private String description;

    @Column(name = "cost", nullable = false)
    @NotNull
    private int cost;

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDate updateDate;

    //Если ставить Lazy и у Restaurant lazy на menu вылетает исключение:
    //org.hibernate.LazyInitializationException: could not initialize proxy [topjava.quest.model.Restaurant#100005] - no Session
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private Restaurant restaurant;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dish() {
    }

    public Dish(Integer id, String description, int cost, LocalDate updateDate, Restaurant restaurant) {
        super(id);
        this.description = description;
        this.cost = cost;
        this.updateDate = updateDate;
        this.restaurant = restaurant;
    }

    public Dish(Integer id, String description, int cost, int restaurant_idTest, LocalDate updateDate) {
        super(id);
        this.description = description;
        this.cost = cost;
        this.updateDate = updateDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

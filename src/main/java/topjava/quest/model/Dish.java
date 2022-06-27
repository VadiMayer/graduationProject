package topjava.quest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Dish.GET_ALL_FOR_RESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id ORDER BY d.updateDate DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.GET_BETWEEN_DATE,
                query = "SELECT d FROM Dish d WHERE d.updateDate >= :startDate AND d.updateDate < :endDate ORDER BY d.updateDate DESC ")
})
@Entity
@Table(name = "restaurant_dishes", uniqueConstraints = @UniqueConstraint(columnNames = "restaurant_id", name = "dishes_unique_restaurant_idx"))
public class Dish extends AbstractNamedEntity {
    public static final String GET_ALL_FOR_RESTAURANT = "Dish.getAllForRestaurant";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN_DATE = "Dish.getBetween";

    @Column(name = "cost", nullable = false)
    @NotNull
    private int cost;

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    //Для тестов
    private int restaurant_id;

    //Для тестов
    public int getRestaurant_idTest() {
        return restaurant_id;
    }

    public Dish() {
    }

    public Dish(Integer id, String name, int cost, int restaurant_id, LocalDateTime updateDate) {
        super(id, name);
        this.cost = cost;
        this.updateDate = updateDate;
        this.restaurant_id = restaurant_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRestaurantId() {
        return restaurant.getRestaurant_id();
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

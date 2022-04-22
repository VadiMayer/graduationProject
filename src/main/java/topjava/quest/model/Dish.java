package topjava.quest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_dishes")
public class Dish extends AbstractNamedEntity {
    public static final String ALL_GET_ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN_DATE = "Dish.getBetween";

    @Column(name = "cost", nullable = false)
    @NotNull
    private int cost;

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, int cost, int restaurant_id, LocalDateTime updateDate) {
        super(id, name);
        this.cost = cost;
        restaurant.setId(restaurant_id);
        this.updateDate = updateDate;
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
}

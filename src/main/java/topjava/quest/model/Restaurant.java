package topjava.quest.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r ORDER BY r.rating DESC"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_BETWEEN_RATING,
                query = "SELECT r FROM Restaurant r WHERE r.rating >= :startRating AND r.rating < :endRating ORDER BY r.rating DESC")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public static final String GET_ALL = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String GET_BETWEEN_RATING = "Restaurant.getBetween";

    @Column(name = "rating", nullable = false)
    @NotNull
    private int rating;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this(0, name, 0, new ArrayList<>());
    }

    public Restaurant(Integer id, String name, int rating, List<Dish> dishes) {
        super(id, name);
        this.rating = rating;
        this.menu = dishes;
    }

    public int getRestaurant_id() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

}

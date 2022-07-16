package topjava.quest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

////@NamedQueries нужны только при JPA реализации ???
//@NamedQueries({
//        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r ORDER BY r.rating DESC"),
//        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
//        @NamedQuery(name = Restaurant.GET_BETWEEN_RATING,
//                query = "SELECT r FROM Restaurant r WHERE r.rating >= :startRating AND r.rating < :endRating ORDER BY r.rating DESC")
//})
@Entity
@Table(name = "restaurants")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Restaurant extends AbstractNamedEntity {
//    public static final String GET_ALL = "Restaurant.getAll";
//    public static final String DELETE = "Restaurant.delete";
//    public static final String GET_BETWEEN_RATING = "Restaurant.getBetween";

    //FetchType.LAZY оборачивает в прокси, при попытке Jackson сериализации поля в JSON, транзакция уже зарыта, поэтому нужна @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("updateDate DESC")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @BatchSize(size = 200)
    @ApiModelProperty(hidden = true)
    private List<Dish> menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this(0, name, new ArrayList<>());
    }

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.menu = dishes;
    }

    public int getRestaurant_id() {
        return id;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

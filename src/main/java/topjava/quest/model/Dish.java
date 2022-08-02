package topjava.quest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NamedEntityGraph(name = Dish.graph, attributeNodes = {@NamedAttributeNode("restaurant")})
@Table(name = "restaurant_dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "restaurant_id", name = "restaurant_dishes_unique_id_rest")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dish extends AbstractBaseEntity {

    public static final String graph = "Dish.withRestaurants";

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 5, max = 90)
    @ApiModelProperty(example = "New dishes")
    private String description;

    @Column(name = "cost", nullable = false)
    @NotNull
    @ApiModelProperty(example = "600")
    private int cost;

    @Column(name = "update_date", nullable = false)
    @NotNull
    @ApiModelProperty(hidden = true)
    private LocalDate updateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
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

    public Dish(Integer id, String description, int cost, LocalDate updateDate) {
        super(id);
        this.description = description;
        this.cost = cost;
        this.updateDate = updateDate;
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

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", updateDate=" + updateDate +
                '}';
    }
}

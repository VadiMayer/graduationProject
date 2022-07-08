package topjava.quest.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import topjava.quest.model.Dish;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.util.List;

public class RestaurantTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    private final String nameRestaurant;

    @JsonIgnore
    private final int restaurant_id;

    @NotNull
    private List<DishTo> menu;

    private boolean error;

    public Integer getId() {
        return id;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DishTo> getMenu() {
        return menu;
    }

    public void setMenu(List<DishTo> menu) {
        this.menu = menu;
    }

    public RestaurantTo(Integer id, String nameRestaurant, int restaurant_id, List<DishTo> menu) {
        super(id);
        this.nameRestaurant = nameRestaurant;
        this.restaurant_id = restaurant_id;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", nameRestaurant='" + nameRestaurant + '\'' +
                ", restaurant_id=" + restaurant_id +
                ", requiresAnUpdate=" + error +
                '}';
    }
}
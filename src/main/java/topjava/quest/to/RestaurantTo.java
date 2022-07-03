package topjava.quest.to;

import io.swagger.annotations.ApiModelProperty;
import topjava.quest.model.Dish;

import java.util.List;

public class RestaurantTo {

    private final Integer id;

    private final String nameRestaurant;

    private final int restaurant_id;

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
        this.id = id;
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
                ", notRequiresAnUpdate=" + error +
                '}';
    }
}
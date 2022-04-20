package topjava.quest.model;

import java.util.List;
import java.util.Objects;

public class Restaurant extends AbstractNamedEntity {

    private int restaurant_id;

    private int rating;

    private List<Dish> menu;

    public Restaurant(Integer id, String name, int rating, int restaurant_id, List<Dish> dishes) {
        super(id, name);
        this.restaurant_id = restaurant_id;
        this.rating = rating;
        this.menu = dishes;
    }

    public int getRestaurant_id() {
        return restaurant_id;
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

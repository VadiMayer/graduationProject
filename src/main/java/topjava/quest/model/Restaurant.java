package topjava.quest.model;

import java.util.ArrayList;

public class Restaurant extends AbstractNamedEntity {

    private static final int START_SEQ = 100000;

    private static final int restaurant_id = START_SEQ + 1;

    private int rating;

    private ArrayList<Dish> menu;

    public static int getRestaurant_id() {
        return restaurant_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        this.menu = menu;
    }
}

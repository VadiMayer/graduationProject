package topjava.quest.model;

import java.util.ArrayList;

public class Restaurant extends AbstractNamedEntity {

    private int rating;

    private ArrayList<Dish> menu;



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

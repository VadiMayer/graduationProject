package topjava.quest.to;


import java.util.List;

public class RestaurantTo {

    private final Integer id;

    private final String nameRestaurant;

    private final int rating;

    public RestaurantTo(Integer id, String nameRestaurant, int rating) {
        this.id = id;
        this.nameRestaurant = nameRestaurant;
        this.rating = rating;
    }

    public class Menu {

        private final List<DishTo> dishList;

        public Menu(List<DishTo> dishList) {
            this.dishList = dishList;
        }

    }
}
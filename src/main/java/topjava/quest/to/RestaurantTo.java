package topjava.quest.to;

public class RestaurantTo {

    private final Integer id;

    private final String nameRestaurant;

    private final int rating;

    private final int restaurant_id;

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

    public int getRating() {
        return rating;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public RestaurantTo(Integer id, String nameRestaurant, int rating, int restaurant_id) {
        this.id = id;
        this.nameRestaurant = nameRestaurant;
        this.rating = rating;
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", nameRestaurant='" + nameRestaurant + '\'' +
                ", rating=" + rating +
                ", restaurant_id=" + restaurant_id +
                ", notRequiresAnUpdate=" + error +
                '}';
    }
}
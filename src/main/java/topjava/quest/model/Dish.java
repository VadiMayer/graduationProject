package topjava.quest.model;

import java.time.LocalDateTime;

public class Dish extends AbstractNamedEntity {

    private int cost;

    private final int restaurantId;

    private LocalDateTime updateDate;

    public Dish(Integer id, String name, int cost, int restaurantId, LocalDateTime updateDate) {
        super(id, name);
        this.cost = cost;
        this.restaurantId = restaurantId;
        this.updateDate = updateDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}

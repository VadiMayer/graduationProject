package topjava.quest.to;

import java.time.LocalDateTime;

public class DishTo {

    private final Integer id;

    private final String name;

    private final int cost;

    private final int restaurantId;

    private final LocalDateTime updateDate;

    private final boolean error;

    public DishTo(Integer id, String name, int cost, int restaurantId, LocalDateTime updateDate, boolean error) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.restaurantId = restaurantId;
        this.updateDate = updateDate;
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public boolean isError() {
        return error;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", updateDate=" + updateDate +
                ", requiresAnUpdate=" + error +
                '}';
    }
}

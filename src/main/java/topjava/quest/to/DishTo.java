package topjava.quest.to;

import java.time.LocalDateTime;

public class DishTo implements Comparable<DishTo> {

    private final Integer id;

    private final String name;

    private final int cost;

    private final int restaurant_Id;

    private final LocalDateTime updateDate;

    private final boolean error;

    public DishTo(Integer id, String name, int cost, int restaurant_Id, LocalDateTime updateDate, boolean error) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.restaurant_Id = restaurant_Id;
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
        return restaurant_Id;
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

    @Override
    public int compareTo(DishTo o) {
        return this.restaurant_Id - o.getRestaurantId();
    }
}

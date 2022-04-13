package topjava.quest.to;

import java.time.LocalDateTime;

public class DishTo {

    private final Integer id;

    private final String name;

    private final int cost;

    private final LocalDateTime updateDate;

    private final boolean requiresAnUpdate;

    public DishTo(Integer id, String name, int cost, LocalDateTime updateDate, boolean requiresAnUpdate) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.updateDate = updateDate;
        this.requiresAnUpdate = requiresAnUpdate;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public boolean isRequiresAnUpdate() {
        return requiresAnUpdate;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", updateDate=" + updateDate +
                ", requiresAnUpdate=" + requiresAnUpdate +
                '}';
    }
}

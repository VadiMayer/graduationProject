package topjava.quest.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Dish extends AbstractNamedEntity {

    private int cost;

    private LocalDateTime updateDate;

    public Dish(Integer id, String name, int cost, LocalDateTime updateDate) {
        super(id, name);
        this.cost = cost;
        this.updateDate = updateDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}

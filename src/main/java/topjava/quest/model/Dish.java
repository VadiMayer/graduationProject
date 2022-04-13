package topjava.quest.model;

import java.time.LocalDateTime;

public class Dish extends AbstractNamedEntity {

    private int cost;

    private LocalDateTime updateDate;


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

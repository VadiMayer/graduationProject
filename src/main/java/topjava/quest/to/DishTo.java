package topjava.quest.to;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class DishTo extends BaseTo implements Comparable<DishTo> {

    @NotBlank
    @Size(min = 6, max = 120)
    @ApiModelProperty(example = "Example food")
    private final String name;

    @NotNull
    @Range(min = 2, max = 7000)
    @ApiModelProperty(example = "500", position = 1)
    private final Integer cost;

    @NotNull
    @ApiModelProperty(example = "100006", position = 2)
    private final Integer restaurant_Id;

    private final LocalDateTime updateDate;

    @ApiModelProperty(example = "false")
    private final boolean error;

    public DishTo(Integer id, String name, Integer cost, int restaurant_Id, LocalDateTime updateDate, boolean error) {
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

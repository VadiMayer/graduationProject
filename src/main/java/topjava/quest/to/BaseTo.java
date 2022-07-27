package topjava.quest.to;

import io.swagger.annotations.ApiModelProperty;
import topjava.quest.HasId;

public class BaseTo implements HasId {

    @ApiModelProperty(hidden = true)
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}

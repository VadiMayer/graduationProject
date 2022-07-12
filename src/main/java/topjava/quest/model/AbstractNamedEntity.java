package topjava.quest.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//Аннотация для формирования наследования, вытянуть из базы через select не получится, напрямую таблицы нет, как у Entity
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name", nullable = false)
    @ApiModelProperty(example = "New mame")
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + name + ")";
    }
}

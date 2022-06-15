package topjava.quest.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;

//Аннотация для формирования наследования, вытянуть из базы через select не получится, напрямую таблицы нет, как у Entity
@MappedSuperclass
//Аннотация говорит, что отражение будет делаться напрямую через поля, не гетт и сетт
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {

    public static final int START_SEQ = 100000;

    //Указывает, что данное поле относится в таблице к id.
    @Id
    //В аннотации @SequenceGenerator атрибутом sequenceName определяется генератор последовательности.
    // Атрибутом name данная аннотация связывается с @GeneratedValue.
    //Если бы значение allocationSize равнялось 5, то значение 1-го идентификатора равнялось 25.
    // По умолчанию значение allocationSize равно 50, если не указывать его в аннотации.
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    //Аннотация @GeneratedValue атрибутом strategy определяет стратегию генерации уникального идентификатора
    // с использованием SEQUENCE. Атрибут generator связывает данную аннотацию с аннотацией @SequenceGenerator.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int haveId() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}

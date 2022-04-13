package topjava.quest.util;

import topjava.quest.model.AbstractBaseEntity;

public class ValidationUtil {

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.haveId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}

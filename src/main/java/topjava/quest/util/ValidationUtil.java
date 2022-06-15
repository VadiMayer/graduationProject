package topjava.quest.util;

import topjava.quest.model.AbstractBaseEntity;
import topjava.quest.util.exception.NotFoundException;

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

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        throwException(found, "id=" + id);
    }

    public static void throwException(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }
}

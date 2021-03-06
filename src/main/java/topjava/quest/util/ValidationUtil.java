package topjava.quest.util;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import topjava.quest.HasId;
import topjava.quest.util.exception.NotFoundException;
import topjava.quest.util.exception.TimeIsUpException;

import java.time.LocalTime;

public class ValidationUtil {

    public static void checkNew(HasId entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId entity, int id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
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

    public static void checkingTheTime(LocalTime rightNow) {
        if (rightNow.isAfter(LocalTime.of(11, 0))) {
            throw new TimeIsUpException("You can't vote anymore today. " +
                    "After 11 am, come back tomorrow. " +
                    "You must to do it until 11.");
        }
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }
}

package topjava.quest.util;

import topjava.quest.model.AbstractBaseEntity;

public class SecurityUtil {
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int authUserId() {
        return id;
    }

    public static void setId(int id) {
        SecurityUtil.id = id;
    }
}

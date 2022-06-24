package topjava.quest;

import org.springframework.lang.NonNull;
import org.springframework.test.context.support.DefaultActiveProfilesResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActiveDbProfileResolver extends DefaultActiveProfilesResolver {

    @Override
    public @NonNull
    String[] resolve(@NonNull Class<?> testClass) {
        List<String> profiles = new ArrayList<>(Arrays.asList(super.resolve(testClass)));
        profiles.add("postgres");
        return profiles.toArray(String[]::new);
    }
}

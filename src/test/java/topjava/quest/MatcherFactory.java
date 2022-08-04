package topjava.quest;

import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;


public class MatcherFactory {

    public static <T> Matcher<T> usingAssertions(Class<T> tClass, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion) {
        return new Matcher<>(tClass, assertion, iterableAssertion);
    }

    public static <T> Matcher<T> usingIgnoringFieldsComparator(Class<T> tClass, String... ignoredFields) {
        return usingAssertions(tClass,
                (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields(ignoredFields).isEqualTo(e),
                (a, e) -> assertThat(a).usingRecursiveFieldByFieldElementComparatorIgnoringFields(ignoredFields).isEqualTo(e));
    }

    public static class Matcher<T> {

        private final Class<T> tClass;
        private final BiConsumer<T, T> assertion;
        private final BiConsumer<Iterable<T>, Iterable<T>> iterableAssertions;

        private Matcher(Class<T> tClass, BiConsumer<T, T> assertion, BiConsumer<Iterable<T>, Iterable<T>> iterableAssertions) {
            this.tClass = tClass;
            this.assertion = assertion;
            this.iterableAssertions = iterableAssertions;
        }

        @SafeVarargs
        public final void assertMatch(Iterable<T> actual, T... expected) {
            assertMatch(actual, List.of(expected));
        }

        public void assertMatch(T actual, T expected) {
            assertion.accept(actual, expected);
        }

        public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
            iterableAssertions.accept(actual, expected);
        }
    }
}

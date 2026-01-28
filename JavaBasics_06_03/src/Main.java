/**
 * Chapter 6.3 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.3 tests
 */
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                ternaryOperator(Objects::isNull, obj -> 0, CharSequence::length)
                        .apply("Это же п....ц какой-то!")
        );
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return  t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }
}

/**
 * Chapter 6.1 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.1 tests
 */
import java.util.Optional;
public class Main {
    public static void main(String[] args) {

        Pair <Integer,String> pair1 = Pair.of(1,"Hello");
        Integer i = pair1.getFirst();
        String s = pair1.getSecond();

        Pair<Integer,String> pair2 = Pair.of(1,"Hello");

        boolean mustBeTrue = pair1.equals(pair2);
        boolean mustAlsoBeTrue = pair1.hashCode() == pair2.hashCode();

        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);

        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("Boris");
        System.out.println(name);

        Optional<String> opt = Optional.ofNullable("Wine again");
        opt.ifPresent(name11 -> System.out.println(name11));
    }
}

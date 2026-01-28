/**
 * Chapter 6.2 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.2 tests
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String nullName = null;
        String name = Optional.ofNullable(nullName).orElse("Boris");
        System.out.println(name);

        Optional<String> opt = Optional.ofNullable("Wine again");
        opt.ifPresent(name11 -> System.out.println(name11));

        List<String> strList = new ArrayList<>(Arrays.asList("aaa","bbb","ccc","aaa"));

        strList.forEach(System.out::println);

        Set<Integer> set1 = new LinkedHashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new LinkedHashSet<>(Arrays.asList(0, 1, 2));

        System.out.println(set1);
        System.out.println(set2);
        System.out.println(symmetricDifference(set1, set2));
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        return new HashSet<T>(){
            {
                addAll(new HashSet<T>(set1){ { removeAll(set2); } });
                addAll(new HashSet<T>(set2){ { removeAll(set1); } });
            }
        };
    }

    public static <T> Set<T> symmetricDifference1(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> result = new HashSet<>();
        set1.forEach(elem -> {if (!set2.contains(elem)) result.add(elem);});
        set2.forEach(elem -> {if (!set1.contains(elem)) result.add(elem);});
        return result;
    }

    public static <T> Set<T> symmetricDifference2(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> result = new HashSet<>();
        for (T element : set1) {
            if (!set2.contains(element)) {
                result.add(element);
            }
        }
        for (T element : set2) {
            if (!set1.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}

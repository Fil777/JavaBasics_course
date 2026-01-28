/**
 * Chapter 6.4 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.4 tests
 */

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        pseudoRandomStream(13).limit(10).forEach(System.out::println);
    }
    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, n -> ((n * n) / 10) % 1000);
    }
}

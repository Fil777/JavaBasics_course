/**
 * Chapter 6.4 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.4 tests
 */

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Map;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) throws IOException {
        // Для отладки создаём входной поток слов
        String inStr1 = "Мама мыла-мыла-мыла раму!";
        String inStr2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        System.setIn(new ByteArrayInputStream(inStr2.getBytes(StandardCharsets.UTF_8)));

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("[^a-zA-Zа-яА-Я0-9']+"))
                            .map(String::toLowerCase)
                            .collect(groupingBy(identity(), counting()))
                            .entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue(reverseOrder())
                                    .thenComparing(Map.Entry.comparingByKey()))
                            .limit(10)
                            .map(Map.Entry::getKey)
                    )
                    .forEach(System.out::println);
        }
    }
}
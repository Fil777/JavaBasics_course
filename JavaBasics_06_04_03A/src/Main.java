/**
 * Chapter 6.4 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 6.4 tests
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String inStr1 = "Мама мыла-мыла-мыла раму!";
        String inStr2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.";
        System.setIn(new ByteArrayInputStream(inStr2.getBytes(StandardCharsets.UTF_8)));
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))
                .lines() // читаем все строки
                .flatMap(line -> Arrays.stream(line.split("[^\\p{L}\\p{Nd}]+"))) // разбиваем на слова
                .filter(s -> !s.isEmpty()) // отбрасываем пустые
                .map(String::toLowerCase) // приводим к нижнему регистру
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // подсчет
                .entrySet()
                .stream()
                .sorted(Comparator
                        .comparingLong((Map.Entry<String, Long> e) -> -e.getValue()) // по убыванию частоты
                        .thenComparing(Map.Entry::getKey)) // лексикографически если частота равна
                .limit(10) // только первые 10
                .forEach(entry -> System.out.println(entry.getKey())); // печать слов
    }
}
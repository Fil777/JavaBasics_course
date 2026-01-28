/**
 * Chapter 5.3 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 5.3 tests
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double sum = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                sum += Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
            } finally {
                scanner.close();
            }
        }
        System.out.printf("%.6f", sum);
    }
}
/**
 * Chapter 5.2 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 5.2 tests
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setIn(new ByteArrayInputStream(new byte[]{65, 13, 10, 10, 13}));

        int byteNext;
        int byteCurrent = System.in.read();
        while (byteCurrent != -1) {
            byteNext = System.in.read();
            if (!(byteCurrent == 13 & byteNext == 10)) {
                System.out.write(byteCurrent);
            }
            byteCurrent = byteNext;
        }
        System.out.flush();
    }
}
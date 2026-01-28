import java.io.*;
import java.util.Arrays;

/**
 * Chapter 5.2 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 5.2 tests
 */

public class MyTestScripts {

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 5.2";
        System.out.println("\n" + welcome +
                Arrays.toString(args).
                        replace('[', ' ').
                        replace(']', ' ').
                        replaceAll(",", "") + "!\n");
        System.out.println("-------------------------------------------------------");

        InputStream inputStream = new ByteArrayInputStream(new byte[] { 0x33, 0x45, 0x01});

        try {
            System.out.println(checkSumOfStream(inputStream));
        } catch (IOException e) {}

        System.out.println("-------------------------------------------------------");
    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int inByte;
        int crcSum = 0;
        while ((inByte = inputStream.read()) != -1) {
            crcSum = Integer.rotateLeft(crcSum, 1) ^ inByte;
        }
        return crcSum;
    }


}

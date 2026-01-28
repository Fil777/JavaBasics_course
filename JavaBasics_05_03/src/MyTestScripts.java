import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

        String s = "Ы";
        byte[] bytes = new byte[8];
        bytes = s.getBytes(StandardCharsets.UTF_8);

        InputStream inputStream = new ByteArrayInputStream(bytes);

        System.out.println(s);
        try {
            getByte(inputStream);
        } catch (IOException e) {
        }
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        System.out.println("-------------------------------------------------------");

        bytes = s.getBytes();
        for (byte b : bytes) {
            System.out.println(b & 0xFF);
        }

        System.out.println("-------------------------------------------------------");

        char c = StandardCharsets.UTF_8.encode("Ы").getChar();
        long g = (long) c;
        System.out.println(g / 256);
        System.out.println(g % 256);

        System.out.println("-------------------------------------------------------");

        try {
            Writer writer = new OutputStreamWriter(System.out, StandardCharsets.US_ASCII);
            writer.write('ы');
            writer.flush();
        } catch (IOException e) {
        }

        System.out.println("-------------------------------------------------------");

        InputStream inputStream2 = new ByteArrayInputStream(new byte[]{48, 49, 50, 51});
        Charset charset = StandardCharsets.US_ASCII;
        try {
            System.out.println(readAsString(inputStream2, charset));
        } catch(IOException e) { }

        System.out.println("-------------------------------------------------------");

    }

    public static void getByte(InputStream inputStream) throws IOException {
        int inByte;
        while ((inByte = inputStream.read()) != -1) {
            System.out.println(inByte);
        }
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        return new String(bytes,charset).trim();
    }
}


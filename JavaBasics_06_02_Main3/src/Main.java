import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String numStr = "1 2 3 4 5 6 7";
        System.setIn(new ByteArrayInputStream(numStr.getBytes(StandardCharsets.UTF_8)));
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;
        String[] words = new StringBuilder(scanner.nextLine()).reverse().toString().split(" ");
        for (int i = 1; i < words.length; i+= 2) {
            System.out.print(words[i]+" ");
        }
    }
}

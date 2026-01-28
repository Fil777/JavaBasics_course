import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String numStr = "1 2 3 4 5 6 7";
        System.setIn(new ByteArrayInputStream(numStr.getBytes(StandardCharsets.UTF_8)));
        Deque<Integer> d = new ArrayDeque<>();
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        while (sc.hasNextInt()) {
            counter++;
            int nextInt = sc.nextInt();
            if (counter % 2 == 0) {
                d.offerFirst(nextInt);
            }
        }
        for (int i = 0, size = d.size(); i < size; i++) {
            System.out.print(" " + d.pollFirst());
        }
    }
}
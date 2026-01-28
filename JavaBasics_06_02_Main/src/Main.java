import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
public class Main {
    public static void main(String[] args) {
        String numStr = "1 2 3 4 5 6 7";
        System.setIn(new ByteArrayInputStream(numStr.getBytes(StandardCharsets.UTF_8)));
//        System.out.println(new Scanner(System.in).nextLine());
        Scanner scanner = new Scanner( System.in );
        List<Integer> arr = new ArrayList<>();
        int i = 0;
        while(scanner.hasNext()) {
            if (i % 2 != 0) { arr.add(scanner.nextInt()); }
            else { scanner.nextInt(); }
            i++;
        }
        Collections.reverse(arr);
        arr.forEach(x -> System.out.print(x + " "));
    }
}
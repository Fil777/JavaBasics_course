import java.util.function.DoubleUnaryOperator;

/**
 * Chapter 3.4 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 3.4 tests
 */

public class MyTestScripts {
    ComplexNumber a = new ComplexNumber(1, 1);
    ComplexNumber b = new ComplexNumber(1, 1);
    ComplexNumber cn1 = new ComplexNumber(2.3412,7.2434);
    ComplexNumber cn2 = new ComplexNumber(2.3412,7.2434);
    ComplexNumber cn3 = new ComplexNumber(3.23124,1435.23);

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 3.4";

        MyTestScripts instance = new MyTestScripts();

        if (args.length == 0) {
            System.out.println("\n" + welcome + " !\n");
        }
        else {
            System.out.println("\n" + welcome + args[0] + " " + args[1] + " !\n");
        }

        System.out.println("-------------------------------------------------------");

        System.out.println(instance.a.equals(instance.b));
        System.out.println("instance.a.hashCode():"+instance.a.hashCode()+" instance.b.hashCode():"+instance.b.hashCode());

        System.out.println("-------------------------------------------------------");

        System.out.println("equals cn1 & cn2 true // " + instance.cn1.equals(instance.cn2));
        System.out.println("equals cn2 & cn1 true // " + instance.cn2.equals(instance.cn1));
        System.out.println("equals cn2 & cn3 false // " + instance.cn2.equals(instance.cn3));
        System.out.println("equals cn3 & cn1 false // " + instance.cn3.equals(instance.cn1));
        System.out.println("hash cn1 & cn2 true // " + (instance.cn1.hashCode() == instance.cn2.hashCode()));
        System.out.println("hash cn2 & cn1 true // " + (instance.cn2.hashCode() == instance.cn1.hashCode()));
        System.out.println("hash cn3 & cn1 false // " + (instance.cn3.hashCode() == instance.cn1.hashCode()));
        System.out.println("hash cn3 & cn2 false // " + (instance.cn3.hashCode() == instance.cn2.hashCode()));

        System.out.println("-------------------------------------------------------");

        System.out.println(integrate(x -> 1, 0, 10));//10.0
        System.out.println(integrate(x -> x + 2, 0, 10));//70.0
        System.out.println(integrate( x -> Math.sin(x) / x , 1, 5));//0.603848

        System.out.println("-------------------------------------------------------");

        byte[] example = {72, 101, 108, 108, 111, 33};
        AsciiCharSequence answer = new AsciiCharSequence(example);
        System.out.println("Последовательность - " + answer.toString());//Hello!
        System.out.println("Размер её - " + answer.length());//6
        System.out.println("Символ под № 1 - " + answer.charAt(1));//e
        System.out.println("Подпоследовательность - " + answer.subSequence(1, 5));//ello
        //проверка на нарушение инкапсуляции private поля
        System.out.println(answer.toString());//Hello!
        example[0] = 74;
        System.out.println(answer.toString());//Hello!

    }

    // более быстрый вариант
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        int n = 10000000;
        double h = (b - a) / n;
        double r = 0;
        double x = a;
        for (int i = 0; i < n; i++) {
            r += f.applyAsDouble(x);
            x += h;
        }
        r *= h;
        return r;
    }

    // классический вариант по формуле
    public static double integrate2(DoubleUnaryOperator f, double a, double b) {
        double h = 1e-6;
        double r = 0;
        for (double x = a; x <= b; x += h ) {
            r += f.applyAsDouble(x);
        }
        r *= h;
        return r;
    }

}
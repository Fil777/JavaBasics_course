//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Chapter 2 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @implNote Chapter 2 tests
 */

public class MyTestScripts {
    public static void main(String[] args) {
/**
 * @apiNote just note
 */
//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
// to see how OpenIDE suggests fixing it.
        System.out.println("\n"+"Hello and welcome to Chapter 2, " + args[0] + " " + args[1] + " !");

        System.out.println("-------------------------------------------------------");

        for (int i = 1; i <= 5; i++) {
//TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
// for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
        System.out.println("-------------------------------------------------------");

        System.out.println(booleanExpression(false, false, false, false));
        System.out.println(booleanExpression(true, false, false, false));
        System.out.println(booleanExpression(true, true, false, false));
        System.out.println(booleanExpression(true, true, true, false));
        System.out.println(booleanExpression(true, true, true, true));
        System.out.println(booleanExpression(false, true, false, false));
        System.out.println(booleanExpression(false, true, true, false));
        System.out.println(booleanExpression(false, false, true, false));
        System.out.println(booleanExpression(false, false, false, true));
        System.out.println(booleanExpression(true, false, false, true));
        System.out.println(booleanExpression(true, true, false, true));

        System.out.println("-------------------------------------------------------");

        System.out.println(leapYearCount(13));
        System.out.println(leapYearCount(99));
        System.out.println(leapYearCount(100));
        System.out.println(leapYearCount(101));
        System.out.println(leapYearCount(104));
        System.out.println(leapYearCount(399));
        System.out.println(leapYearCount(400));
        System.out.println(leapYearCount(401));
        System.out.println(leapYearCount(799));
        System.out.println(leapYearCount(800));
        System.out.println(leapYearCount(801));

        System.out.println("-------------------------------------------------------");

        System.out.println(compare(0.1, 0.2, 0.30011 ));

        System.out.println("-------------------------------------------------------");

        double z = 0x0bp3;

        System.out.println(z);

        System.out.println("-------------------------------------------------------");

        int x = 0b10100111;
        System.out.println(x +" = " + Integer.toBinaryString(x));
        int a = flipBit(x, 3);
        System.out.println(a +" = " + Integer.toBinaryString(a));
        int b = flipBit(x, 4);
        System.out.println(b +" = " + Integer.toBinaryString(b));
        int c = flipBit(x, 6);
        System.out.println(c +" = " + Integer.toBinaryString(c));

        System.out.println("-------------------------------------------------------");

        System.out.println(isPowerOfTwo(128));
        System.out.println(isPowerOfTwo(0));
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(-2));

        System.out.println("-------------------------------------------------------");

        System.out.println('A'+"12");
        System.out.println('A'+'1'+"2");
        System.out.println("A"+ ('\t' + '\u0003'));
        System.out.println("A"+12);

        System.out.println("-------------------------------------------------------");

        System.out.println(isPalindrome("Madam, I'm Adam!"));

        System.out.println("-------------------------------------------------------");

        System.out.println(factorial(3));
        System.out.println(factorial(10));

        System.out.println("-------------------------------------------------------");

        System.out.println(Arrays.toString(mergeArrays(new int[]{0,2,2},new int[] {1,3})));

        System.out.println("-------------------------------------------------------");

        String [] roles= {"Городничий","Аммос Федорович","Артемий Филиппович","Лука Лукич"};
        String [] textLines={
            "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
            "Аммос Федорович: Как ревизор?",
            "Артемий Филиппович: Как ревизор?",
            "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
            "Аммос Федорович: Вот те на!",
            "Артемий Филиппович: Вот не было заботы, так подай!",
            "Лука Лукич: Господи боже! еще и с секретным предписаньем!"
        };

        System.out.println(printTextPerRole(roles, textLines));

        System.out.println("-------------------------------------------------------");

    }
    //----------------------------------------------------------------------------------------
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        return  ((a & b) & !(c | d)) |
                ((a & c) & !(b | d)) |
                ((a & d) & !(c | b)) |
                ((b & c) & !(a | d)) |
                ((b & d) & !(a | c)) |
                ((c & d) & !(a | b));
    }

    public static int leapYearCount(int year) {
        return (year / 4) - (year / 100) + (year / 400);
    }

    public static boolean compare(double a, double b, double c) {
        return java.lang.Math.abs( a + b - c ) <= 1E-4;
    }

    public static int flipBit(int value, int bitIndex) {
        return value ^ (1 << --bitIndex);
    }

    public static boolean isPowerOfTwo(int value) {
        return (Integer.bitCount(Math.abs(value)) == 1) ;
    }

    public static boolean isPalindrome(String text) {
        String t1 = text.replaceAll("[^a-zA-Z0-9]","");
        return t1.equalsIgnoreCase((new StringBuilder(t1).reverse()).toString());
    }

    public static BigInteger factorial(int value) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= value; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int idx = 0;
        for (int j : a1) {
            res[idx] = j;
            idx++;
        }
        for (int i : a2) {
            res[idx] = i;
            idx++;
        }
        return Arrays.stream(res).sorted().toArray();
    }

    private static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < roles.length; i++){
            String name = roles[i];
            result.append(name).append(":").append("\n");
            for (int j = 0; j < textLines.length; j++){
                if (textLines[j].startsWith(name + ":")){
                    result.append(j + 1).append(") ").append(textLines[j].replaceFirst(name + ": ", "")).append("\n");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}


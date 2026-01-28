import jdk.jfr.StackTrace;

/**
 * Chapter 4.1 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 4.1 tests
 */

public class MyTestScripts {

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 4.1";

        //MyTestScripts instance = new MyTestScripts();

        if (args.length == 0) {
            System.out.println("\n" + welcome + " !\n");
        } else {
            System.out.println("\n" + welcome + args[0] + " " + args[1] + " !\n");
        }

        System.out.println("-------------------------------------------------------");

        System.out.println(sqrt(-16)); // при отрицательном вылетит как надо

        System.out.println("-------------------------------------------------------");

        MyClass.m1();

        System.out.println("-------------------------------------------------------");

    }

    public static double sqrt(double x) {
        if (x >= 0) return Math.sqrt(x);
        throw new IllegalArgumentException("\nExpected non-negative number, got "+x);
    }

    public class MyClass {
        static void m1() {
            System.out.println(getCallerClassAndMethodName());
            m2();
        }

        static void m2() {
            System.out.println(getCallerClassAndMethodName());
            m3();
        }

        static void m3() {
            System.out.println(getCallerClassAndMethodName());
            m4();
        }

        static void m4() {
            System.out.println(getCallerClassAndMethodName());
        }

        public static String getCallerClassAndMethodName() {
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            //StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length < 3) return null;
            return stackTrace[2].getClassName() + "#" + stackTrace[2].getMethodName();
        }
    }
}

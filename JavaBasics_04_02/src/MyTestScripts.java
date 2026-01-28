/**
 * Chapter 4.2 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 4.2 tests
 */
import java.util.Arrays;
public class MyTestScripts {

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 4.2";

        //MyTestScripts instance = new MyTestScripts();

        System.out.println("\n" + welcome + "," +
                Arrays.toString(args).
                        replace('[',' ').
                            replace(']',' ').
                                replaceAll(",","") + "!\n");

        System.out.println("-------------------------------------------------------");



        System.out.println("-------------------------------------------------------");

    }

//    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
//        RobotConnection conn = null;
//        for (int i = 0; i < 3; i++) {
//            try {
//                conn = robotConnectionManager.getConnection();
//                conn.moveRobotTo(toX,toY);
//                break;
//            } catch (RobotConnectionException e) {
//                if (i >= 2) {
//                    throw new RobotConnectionException(e.toString());
//                }
//            } finally {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
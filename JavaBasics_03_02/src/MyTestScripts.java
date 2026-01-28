/**
 * Chapter 3 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 3.2 tests
 */

import MyRobot.*;

public class MyTestScripts {

    Robot robot = new Robot();

    public static void main(String[] args) {

        MyTestScripts instance = new MyTestScripts();

        System.out.println("\n" + "Hello and welcome to Chapter 3.2, " + args[0] + " " + args[1] + " !");

        System.out.println("-------------------------------------------------------");

        instance.robot.putRobot(-2, -1, Direction.DOWN);
        moveRobot(instance.robot, 0, 1);
        System.out.println("Готово!");

        System.out.println("-------------------------------------------------------");

    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        // устанавливаем направление движения по оси Y
        if (toY > robot.getY()) {
            while (robot.getDirection() != Direction.UP) {
                robot.turnLeft();
            }
        } else {
            while (robot.getDirection() != Direction.DOWN) {
                robot.turnLeft();
            }
        }
        // двигаемся по оси Y
        while (robot.getY() != toY) {
            robot.stepForward();
        }
        // устанавливаем направление движения по оси X
        if (toX > robot.getX()) {
            while (robot.getDirection() != Direction.RIGHT) {
                robot.turnLeft();
            }
        } else {
            while (robot.getDirection() != Direction.LEFT) {
                robot.turnLeft();
            }
        }
        // двигаемся по оси X
        while (robot.getX() != toX) {
            robot.stepForward();
        }
        // пришли
    }
}

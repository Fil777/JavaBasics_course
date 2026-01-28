/**
 * Chapter 4.3 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 4.3 tests
 */
package org.stepic.java.logging;

import java.util.Arrays;
import java.util.logging.*;

public class MyTestScripts {

    private static final Logger LOGGER = Logger.getLogger(MyTestScripts.class.getName());

    public static void main(String[] args) {
        final String welcome = "\n" + "Hello and welcome to Chapter 4.3";

        configureLogging(LOGGER);

        System.out.println("\n" + welcome +
                Arrays.toString(args).
                        replace('[', ' ').
                        replace(']', ' ').
                        replaceAll(",", "") + "!\n");

        System.out.println("-------------------------------------------------------");

        LOGGER.log(Level.FINE, "Started with arguments: {0}", Arrays.toString(args));

        try {
            randomlyFailingAlgorithm();
        } catch (IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Exception caught", e);
            System.exit(2);
        }

        LOGGER.fine("Finished successfully");

        System.out.println("-------------------------------------------------------");

    }

    private static void randomlyFailingAlgorithm () {
        double randomNumber = Math.random();
        LOGGER.log(Level.FINE, "Generated random number: {0}", randomNumber);
        if (randomNumber < 0.5) {
            throw new IllegalStateException("Invalid phase of the Moon");
        }
    }

    private static void configureLogging (Logger LOGGER) {
        //Logger LOGGER = Logger.getLogger("org.stepic.java");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
    }

    private static void configureLogging1 () {
        Logger LOGGER1 = Logger.getLogger("org.stepic.java.logging.ClassA");
        LOGGER1.setLevel(Level.ALL);

        Logger LOGGER2 = Logger.getLogger("org.stepic.java.logging.ClassB");
        LOGGER2.setLevel(Level.WARNING);

        Logger LOGGER3 = Logger.getLogger("org.stepic.java");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        LOGGER3.addHandler(handler);
        LOGGER3.setUseParentHandlers(false);
    }
}
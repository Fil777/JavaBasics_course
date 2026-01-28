/**
 * Chapter 4.3 tests from Java Basics Course:
 * https://stepik.org/course/187/syllabus
 *
 * @author Fil
 * @version 1.0
 * @apiNote Chapter 4.3 test (Mail)
 */

import fil.tests.*;
import fil.tests.Package;

import java.util.Arrays;
import java.util.logging.*;

public class MyTestScripts {

    private static final Logger LOGGER = Logger.getLogger(MyTestScripts.class.getName());

    public static void main(String[] args) {

        final String welcome = "\n" + "Hello and welcome to Chapter 4.3 (Mail)";


        System.out.println("\n" + welcome +
                Arrays.toString(args).
                        replace('[', ' ').
                        replace(']', ' ').
                        replaceAll(",", "") + "!\n");

        configureLogging(LOGGER, Level.FINE, "xml");
        LOGGER.log(Level.FINE, "Started with arguments: {0}", Arrays.toString(args));
        System.out.println("-------------------------------------------------------");

        Sendable[] mailArray = {
                new MailMessage("Andrew", "Nastya", "I kill you!"),
                new MailMessage("Romeo", "Juliet", "I love you!"),
                new MailMessage("Austin Powers", "James Bond", "Big secret!"),
                new MailPackage("Vasya", "Sara", new fil.tests.Package("Flowers", 15)),
                new MailPackage("Sasha", "Juliet", new fil.tests.Package("Flowers", 25)),
                new MailPackage("Andrew", "Nastya", new fil.tests.Package("weapons", 125)),
                new MailPackage("Austin Powers", "James Bond", new fil.tests.Package("weapons", 5)),
                new MailPackage("Austin Powers", "James Bond", new Package("weapons", 500))
        };

        MailService[] msArray = {new Spy(LOGGER), new Thief(20), new Inspector()};

        UntrustworthyMailWorker umw = new UntrustworthyMailWorker(msArray);

        for (Sendable mail : mailArray){
            if(mail instanceof MailPackage) {
                System.out.print("Processing package from " + mail.getFrom() + " to " + mail.getTo());
                System.out.print(" ["+((MailPackage) mail).getContent().getContent()+"]");
            } else {
                System.out.print("Processing message from " + mail.getFrom() + " to " + mail.getTo() + " [Spy works] ");
            }
            try {
                umw.processMail(mail);
            } catch (RuntimeException rex) {
                System.out.print(" ... EXCEPTION : " + rex.toString());
            }
            if(mail instanceof MailPackage) {
                System.out.print(" ["+((MailPackage) mail).getContent().getContent()+"]");
            }
            System.out.println(" ... done");
        }

        System.out.println("\nThief have stolen $" + ((Thief)msArray[1]).getStolenValue() + "!");

        System.out.println("-------------------------------------------------------");
        LOGGER.fine("Finished successfully");

    }

    private static void configureLogging (Logger LOGGER, Level loglevel, String format) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(loglevel);
        if (format == "xml") {
            handler.setFormatter(new XMLFormatter());
        } else {
            handler.setFormatter(new SimpleFormatter());
        }
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false);
        System.out.println("Logger Name = " + LOGGER.getName());
    }


}
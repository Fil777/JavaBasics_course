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
import java.util.logging.*;

public class MyTestScripts {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) {

        final Logger LOGGER = Logger.getLogger(Class.class.getName());

        Sendable[] mailArray = {
                new MailMessage("Andrew", "Nastya", "I kill you!"),
                new MailMessage("Romeo", "Juliet", "I love you!"),
                new MailMessage("Austin Powers", "James Bond", "Big secret!"),
                new MailPackage("Vasya", "Sara", new fil.tests.Package("Flowers", 15)),
                new MailPackage("Sasha", "Juliet", new fil.tests.Package("Flowers", 25)),
                new MailPackage("Andrew", "Nastya", new fil.tests.Package("weapons", 125)),
                new MailPackage("Austin Powers", "James Bond", new fil.tests.Package("weapons", 5)),
                new MailPackage("Austin Powers", "James Bond", new fil.tests.Package("weapons", 500))
        };

        MailService[] msArray = {new Spy(LOGGER), new Thief(20), new Inspector()};

        UntrustworthyMailWorker mailWorker = new UntrustworthyMailWorker(msArray);

        for (Sendable mail : mailArray){
            if(mail instanceof MailPackage) {
                System.out.print("Processing package from " + mail.getFrom() + " to " + mail.getTo());
                System.out.print(" ["+((MailPackage) mail).getContent().getContent()+"]");
            } else {
                System.out.print("Processing message from " + mail.getFrom() + " to " + mail.getTo() + " [Spy works] ");
            }
            try {
                mailWorker.processMail(mail);
            } catch (RuntimeException rex) {
                System.out.print(" ... EXCEPTION : " + rex.toString());
            }
            if(mail instanceof MailPackage) {
                System.out.print(" ["+((MailPackage) mail).getContent().getContent()+"]");
            }
            System.out.println(" ... done");
        }
        System.out.println("\nThief have stolen $" + ((Thief)msArray[1]).getStolenValue() + "!");
    }

    public static class StolenPackageException extends RuntimeException {}

    public static class IllegalPackageException extends RuntimeException {}

    public static class Spy implements MailService {
        private Logger LOGGER;

        public Spy(Logger logger) {
            LOGGER = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if(mail instanceof MailMessage) {
                MailMessage mailMessage = (MailMessage) mail;
                String from = mailMessage.getFrom();
                String to = mailMessage.getTo();
                if (from.equals(AUSTIN_POWERS) || to.equals(AUSTIN_POWERS)) {
                    LOGGER.warning("Detected target mail correspondence: from " + from + " to "
                            + to + " \"" + mailMessage.getMessage() + "\"");
                } else {
                    LOGGER.info("Usual correspondence: from " + from + " to " + to);
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {
        private int minPrice = 0;
        private int stolenValue = 0;

        public Thief(int minPrice){
            this.minPrice = minPrice;
        }

        public int getStolenValue(){
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if(mail instanceof MailPackage) {
                Package pack = ((MailPackage)mail).getContent();
                if(pack.getPrice() >= minPrice){
                    stolenValue += pack.getPrice();
                    mail = new MailPackage(mail.getFrom(), mail.getTo(),new Package("stones instead of "
                            + pack.getContent(), 0));
                }
            }
            return mail;
        }
    }

    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if(mail instanceof MailPackage) {
                Package pack = ((MailPackage)mail).getContent();
                String content = pack.getContent();
                if(content.indexOf("stones instead of ") == 0) {
                    throw new StolenPackageException();
                } else if(content.equals(WEAPONS) || content.equals(BANNED_SUBSTANCE)){
                    throw new IllegalPackageException();
                }
            }
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        private final MailService realMailService = new RealMailService();
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] services){
            mailServices = services;
        }

        public MailService getRealMailService(){
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable processed = mail;
            for (MailService ms : mailServices) {
                processed = ms.processMail(processed);
            }
            return getRealMailService().processMail(processed);
        }
    }
}
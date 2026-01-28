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

    // Непроверяемые исключения – наследники RuntimeException
    public static class StolenPackageException extends RuntimeException {
    }


    public static class IllegalPackageException extends RuntimeException {
    }


    public static class UntrustworthyMailWorker implements MailService {
        // Внутренний экземпляр RealMailService можно объявит прямо в поле,
        // или же, например, в конструкторе.
        private static final MailService realService = new RealMailService();
        private final MailService[] agents;

        public UntrustworthyMailWorker(final MailService[] agents) {
            this.agents = agents;
        }

        public MailService getRealMailService() {
            return realService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService agent : agents) {
                mail = agent.processMail(mail);
            }
            return realService.processMail(mail);
        }
    }


    public static class Spy implements MailService {
        private final Logger logger;

        public Spy(final Logger logger) {
            this.logger = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                String direction = "from " + mail.getFrom() + " to " + mail.getTo();
                if (isTargetMail(mail)) {
                    // Здесь так же неплохо выглядел бы вызов логгера с объектными параметрами.
                    logger.warning(
                            "Detected target mail correspondence: "
                                    + direction + " \"" + ((MailMessage) mail).getMessage() + "\"");
                } else {
                    logger.info("Usual correspondence: " + direction);
                }
            }
            return mail;
        }

        private boolean isTargetMail(Sendable mail) {
            // Сравнивать объекты на равенство лучше всего через метод объекта,
            // который не может равнятся null.
            // Это помогает избегать неожиданных NullPointerException.
            // Если оба объекта могут быть null, может помочь Objects.equals
            return AUSTIN_POWERS.equals(mail.getFrom()) || AUSTIN_POWERS.equals(mail.getTo());
        }
    }


    public static class Inspector implements MailService {

        private static final String[] ILLEGAL_CONTENT =
                new String[]{WEAPONS, BANNED_SUBSTANCE};

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                if (mailPackage.getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
                for (String illegalString : ILLEGAL_CONTENT) {
                    if (mailPackage.getContent().getContent().contains(illegalString)) {
                        throw new IllegalPackageException();
                    }
                }
            }
            return mail;
        }
    }


    public static class Thief implements MailService {
        private final int minValueToSteal;
        private int stolenValue = 0;

        public Thief(int minValueToSteal) {
            this.minValueToSteal = minValueToSteal;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                Package content = ((MailPackage) mail).getContent();
                if (content.getPrice() >= minValueToSteal) {
                    stolenValue += content.getPrice();
                    return new MailPackage(
                            mail.getFrom(), mail.getTo(), stolenPackage(content));
                } else
                    return mail;
            } else {
                return mail;
            }
        }

        private Package stolenPackage(Package content) {
            return new Package("stones instead of " + content.getContent(), 0);
        }
    }

}
package fil.tests;
import java.util.logging.*;
public class Spy implements MailService {
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
            if (isTargetMail(mail)) {
                LOGGER.warning("Detected target mail correspondence: from " + from + " to "
                        + to + " \"" + mailMessage.getMessage() + "\"");
            } else {
                LOGGER.info("Usual correspondence: from " + from + " to " + to);
            }
        }
        return mail;
    }

    private boolean isTargetMail(Sendable mail) {
        return Constants.AUSTIN_POWERS.equals(mail.getFrom()) || Constants.AUSTIN_POWERS.equals(mail.getTo());
    }

}

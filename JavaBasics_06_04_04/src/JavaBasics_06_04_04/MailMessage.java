package JavaBasics_06_04_04;

/**
 * Class emulating mail massages with content
 */
public class MailMessage extends Message {

    private final String content;

    public MailMessage(String from, String to, String content) {
        super(from, to);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

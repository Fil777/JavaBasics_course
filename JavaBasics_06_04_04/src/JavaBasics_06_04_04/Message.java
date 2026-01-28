package JavaBasics_06_04_04;

/**
 * General class for all massages
  */
public class Message {

    private final String from;
    private final String to;

    public Message(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}

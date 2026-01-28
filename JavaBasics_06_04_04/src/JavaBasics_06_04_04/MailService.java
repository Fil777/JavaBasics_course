package JavaBasics_06_04_04;

import java.util.*;
import java.util.function.Consumer;
/**
 * Class emulating mail service
 */
public class MailService<T> implements Consumer {

    private MailBox<T> mailBox;

    public MailService() {
        this.mailBox = new MailBox<T>();
    }

    public Map<String, List<T>> getMailBox() {

        return mailBox;
    }

    @Override
    public void accept(Object o) {

        Message args = (Message) o;
        String recipient = args.getTo();
        T content = null;

        if (this.mailBox.containsKey(recipient)) {

            if (o.getClass().getName().contains(".Salary")) {
                this.mailBox.get(recipient).add((T) ((Salary) o).getSalary());
            } else {
                this.mailBox.get(recipient).add((T) ((MailMessage) o).getContent());
            }

        } else {
            List<T> newMailList = new ArrayList<T>();
            if (newMailList.add(content)) {
                this.mailBox.put(recipient, newMailList);
            } else {
                throw new RuntimeException();
            }
        }

    }

}

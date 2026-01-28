package JavaBasics_06_04_04;

import java.util.*;
import java.util.function.Consumer;
import java.util.Map;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

// Random variables
        String randomFrom = "Vladimir Putin"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "Andrew Filichkin";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 1000000;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

// Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

// Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

// Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";

// Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

// Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

// Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

// Получение и проверка словаря "почтового ящика",
//   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";

    } // end main

//------------------------------------------------------------------------------------------//

    /*
        Обработка сообщений, формирование почтовых ящиков адресатов
    */
    public static class MailService<T> implements Consumer {
        private MailBox<T> mailBox;

        public MailService() {
            this.mailBox = new MailBox<T>();
        }

        public Map<String, List<T>> getMailBox() {

            return mailBox;
        }

        @Override
        public void accept(Object o) {

            AbstractSendable sendable = (AbstractSendable) o;
            String recipient = sendable.getTo();

            if (!this.mailBox.containsKey(recipient)) {
                // адресат новый
                this.mailBox.put(recipient, new ArrayList<T>()); // кладём в ящик адресата с пустым пока списком
            }
            // адресат уже добавлен, список поступлений инициализирован
            // разбираем тип и добавляем значение в ящик
            if (o instanceof MailMessage) {
                this.mailBox.get(recipient).add((T) ((MailMessage) o).getContent()); // записываем содержимое письма
            } else {
                if (o instanceof Salary) {
                    this.mailBox.get(recipient).add((T) ((Salary) o).getSalary()); // записываем зарплату
                }
            }
        }

    }

    /*
        Почтовый ящик, накапливающий входные сообщения для адресата
     */
    public static class MailBox<V> extends LinkedHashMap<String, List<V>> {

        public MailBox() {
            super(new HashMap<String, List<V>>());
        }

        @Override
        public List<V> get(Object key) {
            return super.getOrDefault(key, Collections.EMPTY_LIST);
        }
    }

    /*
        Обработка зарплат (заведение и выдача наружу суммы)
     */

    public static class Salary extends AbstractSendable {
        private final Integer salary;

        public Salary(String from, String to, Integer salary) {
            super(from, to);
            this.salary = salary;
        }

        public Integer getSalary() {
            return salary;
        }
    }

    /*
        Письмо, у которого есть текст, который можно получить с помощью метода getMessage
    */
    public static class MailMessage extends AbstractSendable {
        private final String content;

        public MailMessage(String from, String to, String content) {
            super(from, to);
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return new StringBuilder().append(getFrom()).append(" -> ")
                    .append(getTo()).append(" [ ")
                    .append(getContent()).append(" ]").toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (content != null ? !content.equals(that.content) : that.content != null) return false;

            return true;
        }
    }

    /*
        Абстрактный класс,который позволяет абстрагировать логику хранения
        источника и получателя письма в соответствующих полях класса.
    */
    public abstract static class AbstractSendable implements Sendable {
        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        public String toString() {
            return new StringBuffer().append(from).append(" -> ").append(to).toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            return from.equals(that.from) && to.equals(that.to);
        }

    }

    /*
        Интерфейс: сущность, которую можно отправить по почте.
        У такой сущности можно получить от кого и кому направляется письмо.
    */
    public static interface Sendable {
        String getFrom();
        String getTo();
    }
}
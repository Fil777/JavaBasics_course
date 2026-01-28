package JavaBasics_06_04_04;
/**
 * Class emulating salary processing
  */

public class Salary extends Message {

    private final Integer salary;

    public Salary(String from, String to, Integer salary) {
        super(from, to);
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }
}
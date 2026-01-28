package fil.tests;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Client implements Serializable {
    private long id;
    private String name;
    private LocalDate birthDate;
    private transient long ageInYears;

    public Client( long id, String name, LocalDate birthDate){
        setId(id);
        setName(name);
        setBirthDate(birthDate);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name;}
    public void setName(String name){ this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public long getAgeInYears() {
        if (ageInYears == 0) {
            ageInYears = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        }
        return ageInYears;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(id).append(" ")
                .append(getName()).append(" ")
                .append(getBirthDate()).append(" ")
                .append(getAgeInYears()).toString();
    }
}

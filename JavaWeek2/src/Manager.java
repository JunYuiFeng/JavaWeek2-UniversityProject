import java.time.LocalDate;

public class Manager extends Person{
    public Manager(int id, String username, String password, String firstName, String lastName, LocalDate birthdate) {
        super(id, username, password, firstName, lastName, birthdate);
    }
}

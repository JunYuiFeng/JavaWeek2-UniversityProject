import java.time.LocalDate;
import java.util.Date;

public class Teacher extends Person{
    public Teacher(int id, String username, String password, String firstName, String lastName, LocalDate birthdate) {
        super(id, username, password, firstName, lastName, birthdate);
    }
}

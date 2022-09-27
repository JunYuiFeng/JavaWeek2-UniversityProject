import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Person {
    protected int id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthdate;

    public Person(int id, String username, String password, String firstName, String lastName, LocalDate birthdate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public int getId(int Id) {
        return Id;
    }

    public String getUsername(String username) {
        return username;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    public AccessLevel checkAccessLevel() {
        if (isStudent()) {
            return AccessLevel.Basic;
        }
        else if (isTeacher()) {
            return AccessLevel.Editor;
        }
        return AccessLevel.Admin;
    }

    public Boolean isStudent() {
        return this.getClass() == Student.class;
    }

    public Boolean isTeacher() {
        return this.getClass() == Teacher.class;
    }
    public Boolean isManager() {return  this.getClass() == Manager.class; }

    public int calculateAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    public String toString() {
        return id + "   " + firstName + "  " + lastName + " " + birthdate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " "  + calculateAge();
    }

    public void displayPerson() {
        System.out.println(toString());
    }

    public void displayStudentReport() {
    }

    public void displayStudentReportDetails() {
    }

    public void updateStudentReport() {
    }
}

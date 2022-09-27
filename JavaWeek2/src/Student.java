import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Student extends Person {
    public String group;
    public int javaResult;
    public int csharpResult;
    public int pythonResult;
    public int phpResult;

    public Student(int id, String username, String password, String firstName, String lastName, LocalDate birthdate, String group, int javaResult, int csharpResult, int pythonResult, int phpResult) {
        super(id, username, password, firstName, lastName, birthdate);
        this.group = group;
        this.javaResult = javaResult;
        this.csharpResult = csharpResult;
        this.pythonResult = pythonResult;
        this.phpResult = phpResult;
    }

    public String toString() {
        return super.toString() + " " + group + " ";
    }

    @Override
    public void displayPerson() {
        System.out.println(toString());
    }

    @Override
    public void displayStudentReport() {
        System.out.println(toString() + javaResult + " " + csharpResult + " " + pythonResult + " " + phpResult);
    }

    @Override
    public void displayStudentReportDetails() {
        System.out.printf("""

                         Report of student %s %s

                        Student Id   ................   %S
                        First Name   ................   %s
                        Last Name   .................   %s
                        Age   .......................   %S

                                     COURSE              \s

                        Java   ......................   %S
                        CSharp   ....................   %S
                        Python   ....................   %S
                        PHP   .......................   %S

                                     RESULTS             \s
                                     
                        Result   ....................   %S   
                        Retakes   ...................   %S                             
                        """
                , firstName, lastName, id, firstName, lastName, calculateAge(), javaResult, csharpResult, pythonResult, phpResult, calculateResults(), calculateRetakes());
    }

    @Override
    public void updateStudentReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Current grade Java is: %S Enter (new) grade: ", javaResult);
        int newGradeJava = scanner.nextInt();
        javaResult = newGradeJava;
        System.out.printf("Current grade Csharp is: %S Enter (new) grade: ", csharpResult);
        int newGradeCSharp = scanner.nextInt();
        csharpResult = newGradeCSharp;
        System.out.printf("Current grade Python: %S Enter (new) grade: ", pythonResult);
        int newGradePython = scanner.nextInt();
        pythonResult = newGradePython;
        System.out.printf("Current grade PHP is: %S Enter (new) grade: ", phpResult);
        int newGradePHP = scanner.nextInt();
        phpResult = newGradePHP;
    }

    public String calculateResults() {
        String result;
        if ((javaResult > 55) && (csharpResult > 55) && (pythonResult > 55) && (phpResult > 55)) {
            return result = "Passed";
        }
        else {
            return result = "Not Passed";
        }
    }

    public int calculateRetakes() {
        ArrayList<Integer> courses = new ArrayList();
        int nrOfRetakes = 0;
        courses.add(javaResult);
        courses.add(csharpResult);
        courses.add(pythonResult);
        courses.add(phpResult);

        for (int course: courses) {
            if (course < 55) {
                nrOfRetakes++;
            }
        }

        return nrOfRetakes;
    }
}

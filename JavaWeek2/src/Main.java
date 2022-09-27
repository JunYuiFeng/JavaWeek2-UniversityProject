import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main myProgram = new Main();
        myProgram.start();
    }

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    void start() {
        Scanner scanner = new Scanner(System.in);
        AccessLevel accessLevel = null;
        boolean login = false;
        boolean reportDetailsScreen = false;
        Person reportDetailsStudent = null;
        boolean idFound = false;


        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Student(1, "Emma", "emma", "Emma", "Smith", LocalDate.of(1997, 4, 12) ,"IT-02-A", 54, 50, 66, 54));
        persons.add(new Student(2, "Jack", "brown", "Jack", "Brown", LocalDate.of(1993, 7, 8), "IT-02-A", 72, 68, 43, 95));
        persons.add(new Student(3, "Michael", "Garcia", "Michael", "Garcia", LocalDate.of(1999, 1, 11), "IT-02-A", 45, 71, 55, 84));
        persons.add(new Student(4, "Lisa", "Jones", "Lisa", "Jones", LocalDate.of(2000, 4, 29), "IT-02-A", 98, 64, 81, 22));
        persons.add(new Student(5, "John", "Miller", "John", "Miller", LocalDate.of(2001, 10, 27), "IT-02-A", 100, 94, 99, 93));
        persons.add(new Student(6, "Linda", "Martinez", "Linda", "Martinez", LocalDate.of(2002, 1, 17), "IT-02-A", 55, 79, 81, 55));
        persons.add(new Student(7, "Richard", "Davis", "Richard", "Davis", LocalDate.of(1997, 9, 22), "IT-02-A", 51, 64, 39, 59));
        persons.add(new Student(8, "Mark", "mark", "Mark", "Lopez", LocalDate.of(1996, 9, 12), "IT-02-A", 78, 98, 89, 99));
        persons.add(new Student(9, "Debora", "Hernandez", "Debora", "Hernandez", LocalDate.of(1995, 2, 25), "IT-02-A", 59, 55, 67, 99));
        persons.add(new Teacher(11, "David", "Taylor", "David", "Taylor", LocalDate.of(1965, 12, 6)));
        persons.add(new Teacher(12, "Sophy", "Anderson", "Sophy", "Anderson", LocalDate.of(1987, 6, 1)));
        persons.add(new Manager(99, "Jun", "jun", "Jun", "Feng", LocalDate.of(2003, 2, 16)));

        while (!login) {
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            for (Person person: persons) {
                if (person.checkUsernameAndPassword(loginUsername, loginPassword)) {
                    accessLevel = person.checkAccessLevel();
                    login = true;
                }
            }

            if (login) {
                displayOptions(accessLevel);
            }
            else {
                System.out.println(ANSI_RED + "\ninvalid username or password\n" + ANSI_RESET);
            }
        }

        System.out.print("Please, enter a choice: ");
        char inputChoice = scanner.next().charAt(0);
        char choice = Character.toLowerCase(inputChoice);
        System.out.println();

        while (choice != 'x') {
            switch (choice) {
                case 's':
                    displayListOfStudents(persons);
                    break;
                case 't':
                    displayListOfTeachers(persons);
                    break;
                case 'a':
                    if (reportDetailsScreen) {
                        reportDetailsStudent.updateStudentReport();
                    }
                    else {
                        if (accessLevel.equals(AccessLevel.Editor) || accessLevel.equals(AccessLevel.Admin)) {
                            System.out.println("\n ADD STUDENT\n");
                            scanner.nextLine();
                            int id = persons.size() + 1;

                            System.out.print("Choose a username: ");
                            String username = scanner.nextLine();

                            System.out.print("Choose a password: ");
                            String password = scanner.nextLine();

                            System.out.print("Enter first name: ");
                            String firstName = scanner.nextLine();

                            System.out.print("Enter last name: ");
                            String lastName = scanner.nextLine();

                            System.out.print("Please enter date of birth in MM/DD/YYYY: ");
                            String birthdateInput = scanner.nextLine();
                            DateTimeFormatter dateFormat = (DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                            LocalDate birthdate = LocalDate.parse(birthdateInput, dateFormat);

                            System.out.print("Enter group: ");
                            String group = scanner.nextLine();

                            persons.add(new Student(id, username, password, firstName, lastName, birthdate, group, 0, 0, 0, 0));

                            System.out.println("The data was successfully added!");
                        }
                        else if (accessLevel.equals(AccessLevel.Basic)) {
                            invalidChoicetxt();
                        }
                    }
                    break;
                case 'p':
                    if (accessLevel.equals(AccessLevel.Admin)) {
                        System.out.println("\n ADD TEACHER\n");
                        scanner.nextLine();
                        int id = persons.size() + 1;

                        System.out.print("Choose a username: ");
                        String username = scanner.nextLine();

                        System.out.print("Choose a password: ");
                        String password = scanner.nextLine();

                        System.out.print("Enter first name: ");
                        String firstName = scanner.nextLine();

                        System.out.print("Enter last name: ");
                        String lastName = scanner.nextLine();

                        System.out.print("Please enter date of birth in MM/DD/YYYY: ");
                        String birthdateInput = scanner.nextLine();
                        DateTimeFormatter dateFormat = (DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                        LocalDate birthdate = LocalDate.parse(birthdateInput, dateFormat);

                        persons.add(new Teacher(id, username, password, firstName, lastName, birthdate));

                        System.out.println("The data was successfully added!");
                    }
                    else {
                        invalidChoicetxt();
                    }
                    break;
                case 'n':
                    if (accessLevel.equals(AccessLevel.Admin)) {
                        System.out.println("\n REMOVE STUDENT\n");
                        displayListOfStudents(persons);
                        System.out.print("\n Enter the id of the student you want to remove| Or 0 back to main menu: ");
                        int input = scanner.nextInt();
                        if (input == 0) {
                            break;
                        }
                        persons.removeIf(person -> input == person.id);
                    }
                    else {
                        invalidChoicetxt();
                    }
                    break;
                case 'm':
                    if (accessLevel.equals(AccessLevel.Admin)) {
                        System.out.println("\n REMOVE TEACHER\n");
                        displayListOfTeachers(persons);
                        System.out.print("\n Enter the id of the teacher you want to remove| Or 0 back to main menu: ");
                        int input = scanner.nextInt();
                        if (input == 0) {
                            break;
                        }
                        persons.removeIf(person -> input == person.id);
                    }
                    else {
                        invalidChoicetxt();
                    }
                    break;
                case 'r':
                    displayListOfStudentReport(persons);
                    System.out.print("\n Enter student id (Report Details) | Or 0 back to main menu: ");
                    int displayReportScreenInput = scanner.nextInt();
                    if (displayReportScreenInput != 0) {
                        for (Person person: persons) {
                            if (person.isStudent()) {
                                if (displayReportScreenInput == person.id){
                                    person.displayStudentReportDetails();
                                    idFound = true;
                                    reportDetailsScreen = true;
                                    reportDetailsStudent = person;
                                }
                            }
                        }
                    }
                    else if (!idFound && displayReportScreenInput == 0) {
                    }
                    else if (!idFound) {
                    }
                    break;
                case 'b':
                    reportDetailsScreen = false;
                    break;
                default:
                    invalidChoicetxt();
            }
            if (reportDetailsScreen) {
                System.out.println("\nA. Add (Update) Report  | R. Display Reports  | B. Back to Main  | X. Exit  |\n");
            }
            else {
                displayOptions(accessLevel);
            }
            System.out.print(" Please, enter a choice: ");
            inputChoice = scanner.next().charAt(0);
            choice = Character.toLowerCase(inputChoice);
            System.out.println();
        }
        System.out.println("\n Leaving the program now...");
    }

    private static void invalidChoicetxt() {
        System.out.println(ANSI_RED + "invalid choice" + ANSI_RESET);
    }

    private static void displayOptions(AccessLevel accessLevel) {
        if (accessLevel.equals(AccessLevel.Basic)) {
            System.out.println("\nS. Display Students\t| T. Display Teachers\t| X.Exit |\n");
        }
        else if (accessLevel.equals(AccessLevel.Editor)) {
            System.out.println("\nS. Display Students\t| T. Display Teachers\t| A. Add Students\t| R. Display Report\t| X.Exit |\n");
        }
        else if (accessLevel.equals(AccessLevel.Admin)) {
            System.out.println("\nS. Display Students\t| T. Display Teachers\t| A. Add Students\t| P. Add Teachers\t| N. Remove Students\t| M. Remove Teachers\t| R. Display Report\t| X.Exit |\n");
        }
    }

    private static void displayListOfStudents(ArrayList<Person> persons) {
        System.out.println("LIST OF STUDENTS\n");
        System.out.printf("""
                Id  FirstName  LastName  Age  Group
                **  *********  ********  ***  *****
                """);
        for (Person person: persons) {
            if (person.isStudent()) {
                person.displayPerson();
            }
        }
    }

    private static void displayListOfTeachers(ArrayList<Person> persons) {
        System.out.println("LIST OF TEACHERS\n");
        System.out.printf("""
                Id  FirstName  LastName  Age
                ***  *********  ********  ***
                """);
        for (Person person: persons) {
            if (person.isTeacher()) {
                person.displayPerson();
            }
        }
    }

    private static void displayListOfStudentReport(ArrayList<Person> persons) {
        System.out.println(" STUDENT RESULTS\n");
        System.out.println("Id  FirstName  LastName  Age  Group  Java  CSharp  Python  PHP");
        System.out.println("**  *********  ********  ***  *****  ****  ******  ******  ***");
        for (Person person: persons) {
            if (person.isStudent()) {
                person.displayStudentReport();
            }
        }
    }
}
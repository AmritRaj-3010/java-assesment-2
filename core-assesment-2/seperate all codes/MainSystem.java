import java.util.*;

public class MainSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EnrollmentSystem system = new EnrollmentSystem();

        int choice;

        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Students");
            System.out.println("5. View Enrollments");
            System.out.println("6. Process Enrollment");
            System.out.println("7. Exit");

            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID, Name, Email: ");
                        system.addStudent(new Student(sc.nextInt(), sc.next(), sc.next()));
                        break;

                    case 2:
                        System.out.print("Enter CourseID, Name, Fee: ");
                        system.addCourse(new Course(sc.nextInt(), sc.next(), sc.nextDouble()));
                        break;

                    case 3:
                        System.out.print("Enter StudentID & CourseID: ");
                        system.enrollStudent(sc.nextInt(), sc.nextInt());
                        break;

                    case 4:
                        system.viewStudents();
                        break;

                    case 5:
                        system.viewEnrollments();
                        break;

                    case 6:
                        System.out.print("Enter StudentID to process: ");
                        new EnrollmentThread(sc.nextInt()).start();
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 7);
    }
}
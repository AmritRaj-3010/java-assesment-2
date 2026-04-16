// Import required packages
// java.util → ArrayList, Scanner, HashMap ke liye
import java.io.*;
import java.util.*;


// ================= STUDENT CLASS =================
// Student class individual student ka data store karti hai
class Student {

    int studentId;                          // Unique ID for each student
    String studentName, studentEmail;       // Basic student details
    String className, sectionName;          // Academic details
    public int studentClass;
    public int name;
    public int section;
    public int email;
    public String id;

    // Constructor → object create hote time values assign karta hai
    Student(int studentId, String studentName, String studentEmail, String className, String sectionName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.className = className;
        this.sectionName = sectionName;
    }

    // Method to display student details
    void displayStudentInfo() {
        System.out.println("ID: " + studentId + " | Name: " + studentName +
                " | Email: " + studentEmail + " | Class: " + className + " | Section: " + sectionName);
    }
}


// ================= COURSE CLASS =================
// Course class course information store karti hai
class Course {

    int courseCode;              // Course ID
    String courseTitle;          // Course name
    double courseFee;            // Course fee

    // Constructor
    Course(int courseCode, String courseTitle, double courseFee) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseFee = courseFee;
    }

    // Display course details
    void displayCourseInfo() {
        System.out.println("Course ID: " + courseCode + " | Name: " + courseTitle + " | Fee: " + courseFee);
    }

    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}


// ================= ENROLLMENT SYSTEM =================
// Ye class student aur course ke relation ko manage karti hai
class EnrollmentSystem {

    // HashMap → studentId ke against course list store hoti hai
    HashMap<Integer, ArrayList<Course>> enrollmentData = new HashMap<>();

    // Student ko course me enroll karne ka method
    void enrollStudentToCourse(int studentKey, Course courseRef) {

        // Agar student ka record nahi hai to nayi list create karo
        enrollmentData.putIfAbsent(studentKey, new ArrayList<>());

        // Course add karo
        enrollmentData.get(studentKey).add(courseRef);

        System.out.println("✅ Enrollment Successful!");
    }

    // Sabhi enrollments display karne ka method
    void showAllEnrollments() {

        if (enrollmentData.isEmpty()) {
            System.out.println("⚠ No enrollments found.");
            return;
        }

        // Har student ke enrollments show karo
        for (int stuKey : enrollmentData.keySet()) {
            System.out.println("\nStudent ID: " + stuKey);

            for (Course courseObj : enrollmentData.get(stuKey)) {
                courseObj.displayCourseInfo();
            }
        }
    }
}


// ================= MULTITHREADING =================
// Ye class background me enrollment process run karti hai
class EnrollmentWorker extends Thread {

    int refStudentId;
    Course refCourse;
    EnrollmentSystem refSystem;

    // Constructor
    EnrollmentWorker(int refStudentId, Course refCourse, EnrollmentSystem refSystem) {
        this.refStudentId = refStudentId;
        this.refCourse = refCourse;
        this.refSystem = refSystem;
    }

    // Thread run method
    public void run() {
        try {
            System.out.println("⏳ Processing enrollment...");

            // Delay simulate karne ke liye (processing time)
            Thread.sleep(2000);

            // Enrollment perform karo
            refSystem.enrollStudentToCourse(refStudentId, refCourse);

        } catch (Exception e) {
            System.out.println("❌ Thread error");
        }
    }
}


// ================= FILE HANDLING =================
// Ye class data ko file me save aur load karti hai
class DataFileManager {

    // Students ko file me save karna
    void writeStudentData(ArrayList<Student> studentCollection) {
        try {
            FileWriter fileWriterObj = new FileWriter("students.txt");

            for (Student stuObj : studentCollection) {
                fileWriterObj.write(
                        stuObj.studentId + "," +
                        stuObj.studentName + "," +
                        stuObj.studentEmail + "," +
                        stuObj.className + "," +
                        stuObj.sectionName + "\n"
                );
            }

            fileWriterObj.close();
            System.out.println("💾 Data saved successfully!");

        } catch (Exception e) {
            System.out.println("❌ File save error");
        }
    }

    // File se data load karna
    void readStudentData(ArrayList<Student> studentCollection) {
        try {
            File inputFileObj = new File("students.txt");

            if (!inputFileObj.exists()) return;

            Scanner fileScannerObj = new Scanner(inputFileObj);

            while (fileScannerObj.hasNextLine()) {

                String[] parsedData = fileScannerObj.nextLine().split(",");

                studentCollection.add(new Student(
                        Integer.parseInt(parsedData[0]),
                        parsedData[1],
                        parsedData[2],
                        parsedData[3],
                        parsedData[4]
                ));
            }

            fileScannerObj.close();
            System.out.println("📂 Data loaded successfully!");

        } catch (Exception e) {
            System.out.println("❌ File load error");
        }
    }
}


// ================= MAIN SYSTEM =================
// Ye main class hai jahan se program start hota hai
public class SmartCampus {

    static Scanner userInputScanner = new Scanner(System.in);
    static ArrayList<Student> studentListObj = new ArrayList<>();
    static ArrayList<Course> courseListObj = new ArrayList<>();
    static EnrollmentSystem enrollmentManagerObj = new EnrollmentSystem();
    static DataFileManager fileManagerObj = new DataFileManager();

    public static void main(String[] args) {

        // Program start hote hi data load karo
        fileManagerObj.readStudentData(studentListObj);

        int menuChoice;

        do {
            // Menu display
            System.out.println("\n===== SMART CAMPUS SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Students");
            System.out.println("5. View Courses");
            System.out.println("6. View Enrollments");
            System.out.println("7. Save Data");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            menuChoice = userInputScanner.nextInt();

            switch (menuChoice) {

                case 1: // Add Student
                    userInputScanner.nextLine();

                    System.out.print("Enter ID: ");
                    int newId = userInputScanner.nextInt();
                    userInputScanner.nextLine();

                    System.out.print("Enter Name: ");
                    String newName = userInputScanner.nextLine();

                    System.out.print("Enter Email: ");
                    String newEmail = userInputScanner.nextLine();

                    System.out.print("Enter Class: ");
                    String newClass = userInputScanner.nextLine();

                    System.out.print("Enter Section: ");
                    String newSection = userInputScanner.nextLine();

                    studentListObj.add(new Student(newId, newName, newEmail, newClass, newSection));
                    System.out.println("✅ Student added!");
                    break;

                case 2: // Add Course
                    System.out.print("Enter Course ID: ");
                    int newCourseId = userInputScanner.nextInt();
                    userInputScanner.nextLine();

                    System.out.print("Enter Course Name: ");
                    String newCourseName = userInputScanner.nextLine();

                    System.out.print("Enter Fee: ");
                    double newFee = userInputScanner.nextDouble();

                    courseListObj.add(new Course(newCourseId, newCourseName, newFee));
                    System.out.println("✅ Course added!");
                    break;

                case 3: // Enroll Student
                    System.out.print("Enter Student ID: ");
                    int searchStudentId = userInputScanner.nextInt();

                    System.out.print("Enter Course ID: ");
                    int searchCourseId = userInputScanner.nextInt();

                    Course matchedCourseObj = null;

                    // Course search
                    for (Course tempCourseObj : courseListObj) {
                        if (tempCourseObj.courseCode == searchCourseId) {
                            matchedCourseObj = tempCourseObj;
                        }
                    }

                    // Multithreading enrollment
                    if (matchedCourseObj != null) {
                        new EnrollmentWorker(searchStudentId, matchedCourseObj, enrollmentManagerObj).start();
                    } else {
                        System.out.println("❌ Course not found!");
                    }
                    break;

                case 4: // View Students
                    for (Student stuObj : studentListObj) {
                        stuObj.displayStudentInfo();
                    }
                    break;

                case 5: // View Courses
                    for (Course courseObj : courseListObj) {
                        courseObj.displayCourseInfo();
                    }
                    break;

                case 6: // View Enrollments
                    enrollmentManagerObj.showAllEnrollments();
                    break;

                case 7: // Save Data
                    fileManagerObj.writeStudentData(studentListObj);
                    break;

                case 8: // Exit
                    fileManagerObj.writeStudentData(studentListObj);
                    System.out.println("👋 Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (menuChoice != 8);
    }
}
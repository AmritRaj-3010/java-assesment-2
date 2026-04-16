import java.util.*;

class EnrollmentSystem {
    HashMap<Integer, ArrayList<Course>> enrollments = new HashMap<>();

    void enrollStudent(int studentId, Course course) {
        enrollments.putIfAbsent(studentId, new ArrayList<>());
        enrollments.get(studentId).add(course);
        System.out.println("Student enrolled successfully!");
    }

    void viewEnrollments() {
        for (Integer id : enrollments.keySet()) {
            System.out.println("Student ID: " + id);
            for (Course c : enrollments.get(id)) {
                c.display();
            }
        }
    }

    @SuppressWarnings({ "unused" })
    void enrollStudentToCourse1(int studentId, Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void enrollStudentToCourse1(int studentId, Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void enrollStudentToCourse(int studentId, Course course) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
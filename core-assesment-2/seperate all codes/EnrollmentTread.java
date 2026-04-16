class EnrollmentThread extends Thread {
    int studentId;
    Course course;
    EnrollmentSystem system;

    EnrollmentThread(int studentId, Course course, EnrollmentSystem system) {
        this.studentId = studentId;
        this.course = course;
        this.system = system;
    }

    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " started enrollment...");
            
            Thread.sleep(2000); // simulate delay
            
            system.enrollStudentToCourse(studentId, course);

            System.out.println("Enrollment completed for student " + studentId);

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}
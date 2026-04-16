class Student {
    int id;
    String name;
    String email;
    String studentClass;
    String section;

    Student(int id, String name, String email, String studentClass, String section) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.studentClass = studentClass;
        this.section = section;
    }

    void display() {
        System.out.println(id + " " + name + " " + email + " " + studentClass + " " + section);
    }
}
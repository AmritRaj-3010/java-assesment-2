import java.io.*;
import java.util.*;

class FileHandler {

    void saveStudents(ArrayList<Student> list) {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for (Student s : list) {
                fw.write(s.id + "," + s.name + "," + s.email + "," +
                        s.studentClass + "," + s.section + "\n");
            }
            fw.close();
            System.out.println("Students saved!");
        } catch (Exception e) {
            System.out.println("File Error!");
        }
    }

    void loadStudents(ArrayList<Student> list) {
        try {
            File file = new File("students.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                list.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4]
                ));
            }
            sc.close();
            System.out.println("Students loaded!");
        } catch (Exception e) {
            System.out.println("Load Error!");
        }
    }
}
package repository;

import service.StudentService;
import java.io.*;

public class StudentRepository {
    static String file = "students.dat";

    // Lưu file
    public static void save(StudentService s) throws Exception {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(s);
        }
    }

    // Đọc file
    public static StudentService load() throws Exception {
        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(file))) {
            return (StudentService) in.readObject();
        }
    }
}
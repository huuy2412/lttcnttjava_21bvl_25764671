package entity;
import java.io.Serializable;

public class Student implements Serializable {
    public String id, name;
    public double overallResult = -1;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
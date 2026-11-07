package entity;
import java.io.Serializable;

public class ResultTable implements Serializable {
    public String studentId, subject;
    public double result;

    public ResultTable(String id, String subject, double result) {
        this.studentId = id;
        this.subject = subject;
        this.result = result;
    }
}
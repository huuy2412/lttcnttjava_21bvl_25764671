package service;

import entity.*;
import java.io.Serializable;
import java.util.*;

public class StudentService implements Serializable {
    public List<Student> students = new ArrayList<>();
    public List<ResultTable> scores = new ArrayList<>();

    // Tìm sinh viên
    public Student find(String id) {
        for (Student s : students)
            if (s.id.equals(id)) return s;
        return null;
    }

    // 1. Thêm sinh viên
    public void addStudent(String id, String name) {
        if (id.isEmpty() || name.isEmpty() || find(id) != null)
            throw new IllegalArgumentException("Mã trùng hoặc dữ liệu trống!");
        students.add(new Student(id, name));
    }

    // 2. Nhập hoặc sửa điểm
    public void addScore(String id, String subject, double score) {
        if (find(id) == null) throw new IllegalArgumentException("Không có SV!");
        if (subject.isEmpty() || !Double.isFinite(score) || score < 0 || score > 10)
            throw new IllegalArgumentException("Môn hoặc điểm không hợp lệ!");

        for (ResultTable r : scores)
            if (r.subject.equalsIgnoreCase(subject)) {
                subject = r.subject;
                break;
            }

        for (ResultTable r : scores)
            if (r.studentId.equals(id) && r.subject.equalsIgnoreCase(subject)) {
                r.result = score;
                calculate();
                return;
            }

        scores.add(new ResultTable(id, subject, score));
        calculate();
    }

    // 3. Danh sách sinh viên theo môn
    public void bySubject(String subject) {
        for (ResultTable r : scores)
            if (r.subject.equalsIgnoreCase(subject)) {
                Student s = find(r.studentId);
                System.out.println(s.id + " | " + s.name + " | " + r.result);
            }
    }

    // 4. Điểm trung bình từng môn
    public void average() {
        Map<String, double[]> map = new LinkedHashMap<>();

        for (ResultTable r : scores) {
            double[] a = map.computeIfAbsent(r.subject, k -> new double[2]);
            a[0] += r.result;
            a[1]++;
        }

        for (String mon : map.keySet()) {
            double[] a = map.get(mon);
            System.out.printf("%s: %.2f%n", mon, a[0] / a[1]);
        }
    }

    // 5. Tính điểm tổng kết
    public void calculate() {
        for (Student s : students) {
            double sum = 0;
            int count = 0;

            for (ResultTable r : scores)
                if (r.studentId.equals(s.id)) {
                    sum += r.result;
                    count++;
                }

            s.overallResult = count == 0 ? -1 : sum / count;
        }
    }

    // Xét tốt nghiệp: TB >= 5 và không môn nào dưới 5
    public boolean graduated(Student s) {
        if (s.overallResult < 5) return false;

        for (ResultTable r : scores)
            if (r.studentId.equals(s.id) && r.result < 5)
                return false;

        return true;
    }

    // Xuất danh sách tốt nghiệp và rớt
    public void report() {
        calculate();

        for (int i = 1; i >= 0; i--) {
            boolean pass = i == 1;
            System.out.println(pass ? "\n=== TỐT NGHIỆP ===" : "\n=== RỚT ===");

            for (Student s : students)
                if (graduated(s) == pass)
                    System.out.printf("%s | %s | %.2f%n",
                            s.id, s.name, s.overallResult);
        }
    }

    // Xem toàn bộ dữ liệu
    public void showAll() {
        calculate();

        for (Student s : students)
            System.out.printf("%s | %s | %.2f%n",
                    s.id, s.name, s.overallResult);

        for (ResultTable r : scores)
            System.out.println(r.studentId + " | " +
                    r.subject + " | " + r.result);
    }
}
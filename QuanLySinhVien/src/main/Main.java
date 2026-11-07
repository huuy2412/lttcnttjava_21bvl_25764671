package main;

import service.StudentService;
import repository.StudentRepository;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in, "UTF-8");
    static StudentService sv = new StudentService();

    static String input(String text) {
        System.out.print(text);
        return sc.nextLine().trim();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Nhập sinh viên");
            System.out.println("2. Nhập / sửa điểm");
            System.out.println("3. Danh sách theo môn");
            System.out.println("4. Trung bình từng môn");
            System.out.println("5. Xét tốt nghiệp");
            System.out.println("6. Xem tất cả");
            System.out.println("7. Lưu file");
            System.out.println("8. Đọc file");
            System.out.println("0. Thoát");

            String chon = input("Chọn: ");

            try {
                switch (chon) {
                    case "1":
                        sv.addStudent(input("Mã SV: "), input("Tên SV: "));
                        System.out.println("Đã thêm!");
                        break;

                    case "2":
                        String id = input("Mã SV: ");
                        String mon = input("Môn: ");
                        double diem = Double.parseDouble(
                                input("Điểm: ").replace(',', '.'));
                        sv.addScore(id, mon, diem);
                        System.out.println("Đã lưu điểm!");
                        break;

                    case "3":
                        sv.bySubject(input("Nhập môn: "));
                        break;

                    case "4":
                        sv.average();
                        break;

                    case "5":
                        sv.report();
                        break;

                    case "6":
                        sv.showAll();
                        break;

                    case "7":
                        StudentRepository.save(sv);
                        System.out.println("Đã lưu file!");
                        break;

                    case "8":
                        sv = StudentRepository.load();
                        System.out.println("Đã đọc file!");
                        break;

                    case "0":
                        return;

                    default:
                        System.out.println("Chọn sai!");
                }
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
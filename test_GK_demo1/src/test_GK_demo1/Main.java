package test_GK_demo1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Main {

	static class Node {
		Student data;
		Node left;
		Node right;

		public Node(Student data) {
			this.data = data;
		}
	}

	// =====================================
	// CLASS STUDENT BST
	// =====================================

	static class StudentBST {
		private Node root; // chỉ được truy cập trong class này.
		// Them sinh vien vao BST theo ID
		public void insert(Student student) { // hàm
			root = insert(root, student);
		}
		
//		private: Chỉ sử dụng trong class StudentBST.
//		Node: Hàm trả về một nút.
//		Node node: Nút đang được kiểm tra.
//		Student student: Sinh viên cần thêm.
//		Dấu ,: Ngăn cách hai tham số.
		private Node insert(Node node, Student student) { 
			
			if (node == null) { // nếu node không có giá trị thì thực hiện tạo nút mới chứa sinh viên và kết thúc.
				return new Node(student);
			}

			if (student.getId() < node.data.getId()) {// nếu ID sv cần thêm nhỏ hơn ID sv đang ở nút đó
				node.left = insert(node.left, student); // nút con bên trái gọi lại hàm insert đêr thưcj hiện tìm tiếp tục vị trí bên trái
			} else if (student.getId() > node.data.getId()) {
				node.right = insert(node.right, student);
			} else {
				throw new IllegalArgumentException("ID trung: " + student.getId()); // throw: ném ra ngoại lệ, tạo 1 ngoại lệ thông báo + với ID bị trùng
			}
			return node; // trả lại nút hiện tại sau khi cập nhật
		}

		// Duyet cay theo thu tu trai - goc - phai
		public List<Student> getAllById() { // kiểu dữ liệu trả về là danh sách sinh viên
			List<Student> students = new ArrayList<>(); // tạo 1 danh sách rỗng chứa cac sinh viên từ cây
			inOrder(root, students);// gọi hàm duyệt cây inOrder()
			return students;
		}

		private void inOrder(Node node, List<Student> students) {
			if (node == null) {
				return; // nếu nút đang duyệt không tồn tại thì kết thúc
			}
			inOrder(node.left, students);
			students.add(node.data);// ;thêm sinh viên của nút hiện tại vào danh sách
			inOrder(node.right, students);
		}

		// Tim sinh vien theo ID
		public Student find(int id) {// hamf trả về 1 đối tượng sinh viên ID cần tìm
			Node current = root;
			while (current != null) {// còn nút để kiểm tra thì tiếp tục tìm
				if (id == current.data.getId()) {
					return current.data;// nếu id cần tìm bằng với id nút hiện tại thì trar về sinh viên và kết thúc
				}
				if (id < current.data.getId()) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			return null;
		}

		// Xoa sinh vien theo ID
		public boolean delete(int id) {
			if (find(id) == null) {
				return false;
			}
			root = delete(root, id);// gọi hàm xóa lần thứ 2 thực hiện và kết thúc
			return true;
		}

		private Node delete(Node node, int id) {
			if (node == null) {
				return null;
			}
			if (id < node.data.getId()) {
				node.left = delete(node.left, id);
			} else if (id > node.data.getId()) {
				node.right = delete(node.right, id);
			} else {
				// Truong hop node khong co con
				// hoac chi co mot con
				if (node.left == null) {
					return node.right;
				}
				if (node.right == null) {
					return node.left;
				}

				// Truong hop node co hai con
				Node successor = node.right;
				while (successor.left != null) {
					successor = successor.left;
				}
				node.data = successor.data;
				node.right = delete(node.right, successor.data.getId());
			}
			return node;
		}
	}

	// =====================================
	// CAU 1: TAO DANH SACH 10 SINH VIEN
	// =====================================

	public static StudentBST createSampleTree() {
		StudentBST tree = new StudentBST();
		tree.insert(new Student(104, "Le Van Minh", "nam", 6));
		tree.insert(new Student(106, "Do Phuong Mai", "nu", 8.5));
		tree.insert(new Student(102, "Le Thi Hoang Anh", "nu", 2.5));
		tree.insert(new Student(107, "Pham Tuan Hai", "nam", 4));
		tree.insert(new Student(101, "Tran Van Tra", "nam", 9));
		tree.insert(new Student(103, "Le Vu Long", "nam", 3));
		tree.insert(new Student(108, "Phan Thi Yen", "nu", 7));
		tree.insert(new Student(109, "Le Van Chau", "nam", 6.5));
		tree.insert(new Student(112, "Pham Tra My", "nu", 7.5));
		tree.insert(new Student(105, "Dong Van Trang", "nam", 5.5));
		return tree;
	}

	// =====================================
	// HAM XUAT DANH SACH DANG BANG
	// =====================================
//	String title	Nhận tiêu đề bảng
//	List<Student> students	Nhận danh sách sinh viên cần in
	public static void printStudents(String title, List<Student> students) {
		System.out.println("\n" + title + " (" + students.size() + " sinh vien)");
		if (students.isEmpty()) {
			System.out.println("Danh sach rong.");
			return;
		}
		// %-6s căn trái 6 ký tự
		System.out.printf("%-6s | %-22s | %-9s | %s%n", "ID", "Ho va ten", "Gioi tinh", "Diem");
		System.out.println("-----------------------------------------------------");
		for (Student s : students) { // Lấy từng sinh viên trong danh sách để in ra màn hình
			System.out.printf(Locale.US, "%-6d | %-22s | %-9s | %.1f%n", s.getId(), s.getName(), s.getGender(),
					s.getResult());
		}
	}

	// =====================================
	// CAU 2: THONG KE SINH VIEN
	// =====================================
	private static void printStatistics(StudentBST tree) {
		List<Student> all = tree.getAllById();
		int total = all.size();
		if (total == 0) {
			System.out.println("Cay rong: khong co sinh vien.");
			return;
		}

		double sum = 0;
		int gioi = 0;
		int kha = 0;
		int trungBinh = 0;
		int yeu = 0;

		Student namMax = null;
		Student namMin = null;

		for (Student s : all) {
			double score = s.getResult();//Lấy điểm của sinh viên hiện tại và lưu vào biến score
			sum += score;//sum = sum + score;

			// Xep loai sinh vien
			if (score >= 8) {
				gioi++;
			} else if (score >= 7) {
				kha++;
			} else if (score >= 5) {
				trungBinh++;
			} else {
				yeu++;
			}

			// Tim sinh vien nam co diem cao/thap nhat
			if (s.getGender().equalsIgnoreCase("nam")) {
				if (namMax == null || score > namMax.getResult()) {//score > maleMax.getResult(): Điểm hiện tại cao hơn điểm lớn nhất đã lưu.
					namMax = s;
				}
				if (namMin == null || score < namMin.getResult()) {
					namMin = s;
				}
			}
		}

		// Tong so sinh vien
		System.out.println("\nTong so sinh vien: " + total);

		// Diem trung binh
		System.out.printf(Locale.US, "Diem trung binh: %.2f%n", sum / total);

		// Ty le hoc luc
		System.out.printf(Locale.US, "Gioi (>=8): %d SV - %.1f%%%n", gioi, gioi * 100.0 / total);
		System.out.printf(Locale.US, "Kha (>=7, <8): %d SV - %.1f%%%n", kha, kha * 100.0 / total);
		System.out.printf(Locale.US, "Trung binh (>=5, <7): %d SV - %.1f%%%n", trungBinh, trungBinh * 100.0 / total);
		System.out.printf(Locale.US, "Yeu (<5): %d SV - %.1f%%%n", yeu, yeu * 100.0 / total);

		// Sinh vien nam diem cao/thap nhat
		if (namMax == null) {
			System.out.println("Khong co sinh vien nam.");
		} else {
			System.out.printf(Locale.US, "Nam diem cao nhat: %s (ID %d, %.1f)%n", namMax.getName(), namMax.getId(),
					namMax.getResult());
			System.out.printf(Locale.US, "Nam diem thap nhat: %s (ID %d, %.1f)%n",namMin.getName(), namMin.getId(),
					namMin.getResult());
		}
	}

	// =====================================
	// CAU 4: TACH DANH SACH DAT / ROT
	// VA SAP XEP THEO DIEM HOAC TEN
	// =====================================
	private static void printPassFail(StudentBST tree, boolean sortByName) {
		List<Student> pass = new ArrayList<>();
		List<Student> fail = new ArrayList<>();

		// Tach sinh vien dat va rot
		for (Student s : tree.getAllById()) {
			if (s.getResult() >= 5) {
				pass.add(s);
			} else {
				fail.add(s);
			}
		}

//		- Comparator: Công cụ xác định quy tắc so sánh để sắp xếp.
//		- <Student>: Đối tượng cần sắp xếp là sinh viên.
//		- comparator: Tên biến lưu quy tắc sắp xếp.
//		Lúc này chưa xác định sắp xếp theo điểm hay tên.
//		Dấu :: là cú pháp tham chiếu phương thức trong Java.
//		String.CASE_INSENSITIVE_ORDER
//		So sánh chuỗi không phân biệt chữ hoa/chữ thường.
//		.thenComparing(Student::getName, ...)
//		Nếu hai sinh viên có tên gọi giống nhau, tiếp tục so sánh họ tên đầy đủ.
//		.thenComparingInt(Student::getId)
//		Nếu họ tên cũng giống nhau, dùng ID để quyết định thứ tự.
//		Luồng sắp xếp: Tên gọi A–Z → Nếu trùng tên thì xét họ tên → Nếu vẫn trùng thì xét ID.
		Comparator<Student> comparator;
		if (sortByName) {
			// Sap xep theo ten A-Z
			comparator = Comparator.comparing(Main::getTen, String.CASE_INSENSITIVE_ORDER)
					.thenComparing(Student::getName, String.CASE_INSENSITIVE_ORDER).thenComparingInt(Student::getId);
		} else {
			// Sap xep theo diem tang dan
			comparator = Comparator.comparingDouble(Student::getResult).thenComparingInt(Student::getId);
		}

		pass.sort(comparator);
		fail.sort(comparator);
		
		if (sortByName) {
			printStudents("DANH SACH DAT - THEO TEN", pass);
			printStudents("DANH SACH ROT - THEO TEN", fail);
		} else {
			printStudents("DANH SACH DAT - THEO DIEM", pass);
			printStudents("DANH SACH ROT - THEO DIEM", fail);
		}
	}

	// Lay ten goi tu ho va ten
	private static String getTen(Student student) {
		String[] parts = student.getName().trim().split("\\s+");
		return parts[parts.length - 1];
	}

	// =====================================
	// HAM MAIN: TU DONG THUC HIEN 4 CAU
	// =====================================
	public static void main(String[] args) {
		StudentBST tree = createSampleTree();
		System.out.println("========== BAI THI GIUA KY - STUDENT BST ==========");
		System.out.println("Da dua 10 sinh vien cua de bai vao cay BST.");

		// =====================================
		// CAU 1 (2 DIEM)
		// =====================================
		System.out.println("\n================ CAU 1 (2 DIEM) ================");
		printStudents("DANH SACH TANG DAN THEO ID", tree.getAllById());

		// =====================================
		// CAU 2 (3 DIEM)
		// =====================================
		System.out.println("\n================ CAU 2 (3 DIEM) ================");
		printStatistics(tree);

		// =====================================
		// CAU 3 (2 DIEM)
		// =====================================
		System.out.println("\n================ CAU 3 (2 DIEM) ================");
		// Tim sinh vien ID 105
		int idToFind = 105;
		System.out.println("Tim sinh vien co ID = " + idToFind);
		Student found = tree.find(idToFind);
		if (found == null) {
			System.out.println("Khong tim thay sinh vien.");
		} else {
			List<Student> one = new ArrayList<>();
			one.add(found);
			printStudents("KET QUA TIM KIEM", one);
		}

		// Tao cay rieng de minh hoa xoa
		// Khong anh huong 10 SV cua cau 4
		StudentBST deleteDemo = createSampleTree();
		int idToDelete = 104;
		System.out.println("\nXoa sinh vien co ID = " + idToDelete);

		if (deleteDemo.delete(idToDelete)) {
			System.out.println("Xoa thanh cong!");
			printStudents("DANH SACH SAU KHI XOA", deleteDemo.getAllById());
		} else {
			System.out.println("Khong tim thay sinh vien de xoa.");
		}

		// =====================================
		// CAU 4 (3 DIEM)
		// =====================================
		System.out.println("\n================ CAU 4 (3 DIEM) ================");
		// Sap xep theo diem
		printPassFail(tree, false);
		// Sap xep theo ten
		printPassFail(tree, true);
		System.out.println("\n================ HOAN THANH 4 CAU ================");
	}
}
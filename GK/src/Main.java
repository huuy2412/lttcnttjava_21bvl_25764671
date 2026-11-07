import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Main {
	public static class StudentBST {
		private Node root;

		public void insert(Student student) {
			root = insert(root, student);
		}

		private Node insert(Node node, Student student) {
			if (node == null) {
				return new Node(student);
			}

			if (student.getId() < node.data.getId()) {
				node.left = insert(node.left, student);
			} else if (student.getId() > node.data.getId()) {
				node.right = insert(node.right, student);
			} else {
				System.out.println("lỗi!");
			}
			return node;
		}

		public List<Student> getAllById() {
			List<Student> students = new ArrayList<>();
			inOrder(root, students);
			return students;
		}

		private void inOrder(Node node, List<Student> students) {
			if (node == null) {
				return;
			}
			inOrder(node.left, students);
			students.add(node.data);
			inOrder(node.right, students);
		}

		public Student find(int id) {
			Node node = root;
			while (node != null) {
				if (id == node.data.getId()) {
					return node.data;
				}
				if (id < node.data.getId()) {
					node = node.left;
				} else {
					node = node.right;
				}
			}
			return null;
		}

		public boolean delete(int id) {
			if (find(id) == null) {
				return false;
			}
			root = delete(root, id);
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
				if (node.left == null) {
					return node.right;
				}
				if (node.right == null) {
					return node.left;
				}

				Node sc = node.right;
				while (sc.left != null) {
					sc = sc.left;
				}
				node.data = sc.data;
				node.right = delete(node.right, sc.data.getId());
			}
			return node;
		}
	}

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

	public static void printStudents(String title, List<Student> students) {
		System.out.println("\n===== " + title + " =====");
		if (students.isEmpty()) {
			System.out.println("Danh sach rong.");
			return;
		}
		System.out.printf("%-6s| %-22s|%-9s|%s%n", "ID", "Ho va ten", "Gioi tinh", "Diem");
		System.out.println("-----------------------------------------------------");
		for (Student s : students) {
			System.out.printf("%-6d|%-22s|%-9s|%.1f%n", s.getId(), s.getName(), s.getGender(), s.getResult());
		}
	}

	public static void printStatistics(StudentBST tree) {
		List<Student> all = tree.getAllById();
		int total = all.size();
		if (total == 0) {
			System.out.println("không có sinh viên");
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
			double score = s.getResult();
			sum += score;

			if (score >= 8) {
				gioi++;
			} else if (score >= 7) {
				kha++;
			} else if (score >= 5) {
				trungBinh++;
			} else {
				yeu++;
			}

			if (s.getGender().equalsIgnoreCase("nam")) {
				if (namMax == null || score > namMax.getResult()) {
					namMax = s;
				}
				if (namMin == null || score < namMin.getResult()) {
					namMin = s;
				}
			}
		}

		System.out.printf("Gioi (>=8): %d%n", gioi, gioi * 100.0 / total);
		System.out.printf("Kha (>=7, <8):%d%n", kha, kha * 100.0 / total);
		System.out.printf("Trung binh (>=5, <7):%d%n", trungBinh, trungBinh * 100.0 / total);
		System.out.printf("Yeu (<5):%d%n", yeu, yeu * 100.0 / total);

		if (namMax == null) {
			System.out.println("Khong co sinh vien nam.");
		} else {
			System.out.printf("Nam diem cao nhat:%s (ID %d, %.1f)%n", namMax.getName(), namMax.getId(),
					namMax.getResult());
			System.out.printf("Nam diem thap nhat:%s (ID %d, %.1f)%n", namMin.getName(), namMin.getId(),
					namMin.getResult());
		}
	}

	private static void printDauRot(StudentBST tree, boolean sortByName) {
		List<Student> dau = new ArrayList<>();
		List<Student> rot = new ArrayList<>();

		for (Student s : tree.getAllById()) {
			if (s.getResult() >= 5) {
				dau.add(s);
			} else {
				rot.add(s);
			}
		}
		Comparator<Student> comparator;
		if (sortByName) {
			comparator = Comparator.comparing(Main::getTen, String.CASE_INSENSITIVE_ORDER)
					.thenComparing(Student::getName, String.CASE_INSENSITIVE_ORDER).thenComparingInt(Student::getId);
		} else {
			comparator = Comparator.comparingDouble(Student::getResult).thenComparingInt(Student::getId);
		}

		dau.sort(comparator);
		rot.sort(comparator);

		if (sortByName) {
			printStudents("DANH SACH DAT - THEO TEN", dau);
			printStudents("DANH SACH ROT - THEO TEN", rot);
		} else {
			printStudents("DANH SACH DAT - THEO DIEM", dau);
			printStudents("DANH SACH ROT - THEO DIEM", rot);
		}
	}

	public static String getTen(Student student) {
		String[] parts = student.getName().trim().split("\\s+");
		return parts[parts.length - 1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentBST tree = createSampleTree();
		System.out.println("\n -- CÂU 1: --");
		printStudents("DANH SACH TANG DAN THEO ID", tree.getAllById());

		System.out.println("\n -- CÂU 2: --");
		printStatistics(tree);
		System.out.println("\n-- CÂU 3 --");
		int idtim = 105;
		System.out.println("Tim sinh vien co ID = " + idtim);
		Student found = tree.find(idtim);
		if (found == null) {
			System.out.println("Khong tim thay sinh vien.");
		} else {
			List<Student> one = new ArrayList<>();
			one.add(found);
			printStudents("KET QUA TIM KIEM", one);
		}

		StudentBST delete = createSampleTree();
		int idToDelete = 104;
		System.out.println("\nXoa sinh vien co ID = " + idToDelete);

		if (delete.delete(idToDelete)) {
			System.out.println("Xoa thanh cong!");
			printStudents("DANH SACH SAU KHI XOA", delete.getAllById());
		} else {
			System.out.println("Khong tim thay sinh vien de xoa.");
		}

		System.out.println("\n-- CÂU 4 --");
		printDauRot(tree, false);
		printDauRot(tree, true);
	}
}

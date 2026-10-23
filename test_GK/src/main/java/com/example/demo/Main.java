package com.example.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.Scanner;

public class Main {
	private static final String TREE_FILE = "data/dhcntt21bvl.dat";
	private static final String NAM_FILE = "data/namlist.dat";

	static ArrayList<Customer> namlist = new ArrayList<Customer>();

	// thêm khách hàng
	public static Customer addCustomer(Customer root, Customer e) {
		if (root == null) {
			return e;
		}
		if (e.getCustomer_ID() < root.getCustomer_ID()) {
			root.left = addCustomer(root.left, e);
		}
		if (e.getCustomer_ID() > root.getCustomer_ID()) {
			root.right = addCustomer(root.right, e);
		}
		return root;
	}

	// duyệt cây theo thứ tự tăng dần
	public static void readtree(Customer root) {
		if (root != null) {
			readtree(root.left);
			System.out.printf("%-10d%-30s%-10s%-10d%-15s%n", root.getCustomer_ID(), root.getCustomer_Name(),
					root.getCustomer_Gender(), root.getCustomer_Age(), root.getCustomer_Blood());
			readtree(root.right);
		}
	}

	// tìm id
	public static Customer findCustomer(Customer root, int ID) {
		if (root == null) {
			return null;
		}
		if (ID == root.getCustomer_ID()) {
			return root;
		}
		if (ID < root.getCustomer_ID()) {
			return findCustomer(root.left, ID);
		}

		return findCustomer(root.right, ID);
	}

	// tìm kiếm nút nhỏ nhất bên phải
	public static Customer theleftCustomer(Customer root) {
		if (root == null) {
			return null;
		}
		if (root.left == null) {
			return root;
		}
		return theleftCustomer(root.left);
	}

	// xóa khách hàng
	public static Customer deleteCustomer(Customer root, int ID) {
		if (root == null) {
			return null;
		}
		if (ID == root.getCustomer_ID()) {
			if (root.left == null) {
				return root.right;
			}
			if (root.right == null) {
				return root.left;
			}
			Customer T = theleftCustomer(root.right);
			Customer L = root.left;
			Customer R = deleteCustomer(root.right, T.getCustomer_ID());

			T.left = L;
			T.right = R;

			return T;
		}
		if (ID < root.getCustomer_ID()) {
			root.left = deleteCustomer(root.left, ID);
		} else {
			root.right = deleteCustomer(root.right, ID);
		}
		return root;
	}

	// lấy toàn bộ danh sách
	public static void getAllList(Customer root, ArrayList<Customer> ds) {
		if (root != null) {
			getAllList(root.left, ds);
			ds.add(root);
			getAllList(root.right, ds);
		}
	}

	// tính tuổi trung bình
	public static double avrAGE(Customer root) {
		ArrayList<Customer> ds = new ArrayList<>();
		getAllList(root, ds);
		if (ds.isEmpty()) {
			return 0;
		}
		int tong = 0;
		for (Customer c : ds) {
			tong += c.getCustomer_Age();
		}
		return (double) tong / ds.size();
	}

	// tìm tuổi lớn nhất
	public static Customer maxAge(ArrayList<Customer> ds) {
		if (ds.isEmpty()) {
			return null;
		}
		Customer max = ds.get(0);
		for (Customer c : ds) {
			if (c.getCustomer_Age() > max.getCustomer_Age()) {
				max = c;
			}
		}
		return max;
	}

	// tìm tuổi nhỏ nhất
	public static Customer minAge(ArrayList<Customer> ds) {
		if (ds.isEmpty()) {
			return null;
		}
		Customer min = ds.get(0);
		for (Customer c : ds) {
			if (c.getCustomer_Age() < min.getCustomer_Age()) {
				min = c;
			}
		}
		return min;
	}

	// lọc khách hàng nam
	public static void getNamList(Customer root) {
		if (root != null) {
			getNamList(root.left);
			if ("nam".equalsIgnoreCase(root.getCustomer_Gender())) {
				namlist.add(root);
			}
			getNamList(root.right);
		}
	}

	// hàm lọc danh sách nữ
	public static ArrayList<Customer> getNuList(Customer root) {
		ArrayList<Customer> ds = new ArrayList<>();
		ArrayList<Customer> nuList = new ArrayList<>();

		getAllList(root, ds);

		for (Customer c : ds) {
			if ("nu".equalsIgnoreCase(c.getCustomer_Gender())) {
				nuList.add(c);
			}
		}
		return nuList;
	}

	// lọc theo nhóm máu
	public static ArrayList<Customer> getBloodList(Customer root, String blood) {
		ArrayList<Customer> ds = new ArrayList<>();
		ArrayList<Customer> bloodList = new ArrayList<>();

		getAllList(root, ds);

		for (Customer c : ds) {
			if ("blood".equalsIgnoreCase(c.getCustomer_Blood())) {
				bloodList.add(c);
			}
		}
		return bloodList;
	}

	// lấy tên cuối cùng trong danh sách
	public static String getTen(String hoTen) {
		String[] cacTu = hoTen.trim().split("\\s+");
		return cacTu[cacTu.length - 1];
	}

	private static void printHeader() {
		System.out.printf("%-10s%-30s%-10s%-10s%-15s%n", "ID", "Name", "Gender", "Age", "Blood");
	}

	public static void inCustomer(Customer c) {
		System.out.printf("%-10d%-30s%-10s%-10d%-15s%n", c.getCustomer_ID(), c.getCustomer_Name(),
				c.getCustomer_Gender(), c.getCustomer_Age(), c.getCustomer_Blood());
	}

	public static void inDanhSach(ArrayList<Customer> ds) {
		if (ds.isEmpty()) {
			System.out.println("Danh sach rong!");
			return;
		}
		printHeader();

		for (Customer c : ds) {
			inCustomer(c);
		}
	}

	private static Customer createDemoTree() {
		Customer root = null;
		root = addCustomer(root, new Customer(109, "Phạm Hửu Ý", "nam", 20, "A"));
		root = addCustomer(root, new Customer(101, "Nguyễn Phạm Anh Toàn", "nam", 30, "B"));
		root = addCustomer(root, new Customer(103, "Nguyễn Quốc Qui", "nam", 25, "O"));
		root = addCustomer(root, new Customer(107, "Nguyễn Thị Mỹ Như", "nu", 22, "A"));
		root = addCustomer(root, new Customer(106, "Nguyễn Hoàng Tuấn Anh", "nam", 26, "B"));
		return root;
	}

	// lưu cây BST
	public static boolean Save_to_file(Customer root, String path) {
		try {
			Path file = Path.of(path);
			if (file.getParent() != null) {
				Files.createDirectories(file.getParent());
			}
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
				oos.writeObject(root);
			}

			return true;
		} catch (Exception e) {
			System.err.println("Loi luu BST: " + e.getMessage());
			return false;
		}
	}

	// đọc cây BST
	public static Customer Read_from_file(String path) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			return (Customer) ois.readObject();
		} catch (Exception e) {
			System.err.println("Loi doc BST: " + e.getMessage());
			return null;
		}
	}

	// lưu danh sachs nam
	public static boolean Save_to_file_arraylist(ArrayList<Customer> list, String path) {
		try {
			Path file = Path.of(path);
			if (file.getParent() != null) {
				Files.createDirectories(file.getParent());
			}
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
				oos.writeObject(list);
			}
			return true;
		} catch (Exception e) {
			System.err.println("Loi luu danh sach: " + e.getMessage());
			return false;
		}
	}

	// đọc danh sách nam
	@SuppressWarnings("unchecked")
	public static ArrayList<Customer> Read_from_file_arraylist(String path) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			return (ArrayList<Customer>) ois.readObject();
		} catch (Exception e) {
			System.err.println("Loi doc danh sach: " + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		Customer root;

		// 1. DOC DU LIEU CU HOAC TAO MAU
		if (Files.exists(Path.of(TREE_FILE))) {
			root = Read_from_file(TREE_FILE);
			if (root == null) {
				System.out.println("Khong doc duoc du lieu cu!");
				return;
			}
		} else {
			root = createDemoTree();
			System.out.println("Da tao 5 khach hang mau.");
		}

		try (Scanner sc = new Scanner(System.in)) {

			// CAU 1: NHAP KHACH HANG
			System.out.println("\n===== CAU 1: NHAP KHACH HANG =====");
			System.out.print("Nhap so luong khach hang can them: ");
			int n = sc.nextInt();
			sc.nextLine();

			for (int i = 0; i < n; i++) {
				System.out.println("\nKhach hang thu " + (i + 1));
				System.out.print("ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				if (id <= 0 || findCustomer(root, id) != null) {
					System.out.println("ID khong hop le hoac da ton tai!");
					i--;
					continue;
				}

				System.out.print("Ho ten: ");
				String name = sc.nextLine();
				System.out.print("Gioi tinh (nam/nu): ");
				String gender = sc.nextLine().trim();
				System.out.print("Tuoi: ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.print("Nhom mau (A/B/O): ");
				String blood = sc.nextLine().trim().toUpperCase();
				Customer c = new Customer(id, name, gender, age, blood);

				root = addCustomer(root, c);
			}

			ArrayList<Customer> ds = new ArrayList<>();
			getAllList(root, ds);

			// CAP NHAT NHOM MAU CHO DU LIEU CU
			for (Customer c : ds) {
				if (c.getCustomer_Blood().isBlank() || c.getCustomer_Blood().equalsIgnoreCase("Chua cap nhat")) {
					System.out.print("Nhap nhom mau cho ID " + c.getCustomer_ID() + ": ");
					c.setCustomer_Blood(sc.nextLine().trim().toUpperCase());
				}
			}

			// CAU 2: XUAT DANH SACH
			System.out.println("\n===== CAU 2: DANH SACH =====");
			printHeader();
			readtree(root);

			// CAU 3: THONG KE TUOI
			System.out.println("\n===== CAU 3: THONG KE TUOI =====");
			System.out.printf("Tuoi trung binh: %.2f%n", avrAGE(root));
			if (!ds.isEmpty()) {
				System.out.println("Lon tuoi nhat:");
				printHeader();
				inCustomer(maxAge(ds));
				System.out.println("Nho tuoi nhat:");
				printHeader();
				inCustomer(minAge(ds));
			}

			// CAU 4: LOC KHACH HANG
			System.out.println("\n===== CAU 4: LOC KHACH HANG =====");
			namlist.clear();
			getNamList(root);
			System.out.println("Danh sach NAM:");
			inDanhSach(namlist);
			System.out.println("Danh sach NU:");
			inDanhSach(getNuList(root));
			System.out.println("Nhom mau A:");
			inDanhSach(getBloodList(root, "A"));
			System.out.println("Nhom mau B:");
			inDanhSach(getBloodList(root, "B"));
			System.out.println("Nhom mau O:");
			inDanhSach(getBloodList(root, "O"));

			// CAU 5: SAP XEP NAM THEO TEN
			System.out.println("\n===== CAU 5: SAP XEP NAM =====");

			namlist.sort(new NameComparator());
			inDanhSach(namlist);

			// CAU 6: THONG KE TUOI NAM
			System.out.println("\n===== CAU 6: THONG KE TUOI NAM =====");
			if (!namlist.isEmpty()) {
				System.out.println("Nam lon tuoi nhat:");
				printHeader();
				inCustomer(maxAge(namlist));
				System.out.println("Nam nho tuoi nhat:");
				printHeader();
				inCustomer(minAge(namlist));
			} else {
				System.out.println("Khong co khach hang nam!");
			}

			// CAU 7: TIM THEO ID
			System.out.println("\n===== CAU 7: TIM KIEM =====");

			System.out.print("Nhap ID can tim: ");
			int id = sc.nextInt();
			Customer found = findCustomer(root, id);
			if (found == null) {
				System.out.println("Khong tim thay!");
			} else {
				printHeader();
				inCustomer(found);
			}

			// CAU 8: XOA THEO ID
			System.out.println("\n===== CAU 8: XOA KHACH HANG =====");
			System.out.print("Nhap ID can xoa (-1 de bo qua): ");
			id = sc.nextInt();
			if (id != -1) {
				if (findCustomer(root, id) == null) {
					System.out.println("Khong tim thay ID!");
				} else {
					root = deleteCustomer(root, id);
					System.out.println("Da xoa ID: " + id);
				}
			}

			System.out.println("Danh sach sau khi xoa:");
			printHeader();
			readtree(root);

			// CAP NHAT DANH SACH NAM
			namlist.clear();
			getNamList(root);
			namlist.sort(new NameComparator());

			// CAU 9: LUU VA DOC FILE
			System.out.println("\n===== CAU 9: LUU VA DOC FILE =====");
			boolean savedTree = Save_to_file(root, TREE_FILE);
			boolean savedMale = Save_to_file_arraylist(namlist, NAM_FILE);
			if (savedTree && savedMale) {
				System.out.println("Da luu file thanh cong!");
				Customer rootCheck = Read_from_file(TREE_FILE);
				ArrayList<Customer> namCheck = Read_from_file_arraylist(NAM_FILE);
				if (rootCheck != null && namCheck != null) {
					System.out.println("Doc file thanh cong!");
					System.out.println("So khach hang nam: " + namCheck.size());
					printHeader();
					readtree(rootCheck);
				}
			}
		}
	}

}

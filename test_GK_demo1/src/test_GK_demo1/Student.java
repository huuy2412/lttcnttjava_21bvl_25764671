package test_GK_demo1;

public class Student {

	// Thuoc tinh sinh vien
	private int id;
	private String name;
	private String gender;
	private double result;

	// Constructor khong tham so
	public Student() {
	}

	// Constructor day du tham so
	public Student(int id, String name, String gender, double result) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.result = result;
	}

	// Getter va Setter ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Getter va Setter Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getter va Setter Gender
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// Getter va Setter Result
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
}

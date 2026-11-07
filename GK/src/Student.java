
public class Student {

	private int id;
	private String name;
	private String gender;
	private double result;

	public Student(int id, String name, String gender, double result) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.result = result;
	}

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}

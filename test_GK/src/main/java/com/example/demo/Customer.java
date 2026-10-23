package com.example.demo;

import java.io.Serializable;

//implements Serializable	Cho phép ghi đối tượng xuống file .dat
//private int customer_ID	Lưu mã số khách hàng
//Customer left, right	Hai nhánh trái và phải trong cây BST
//getCustomer_ID()	Lấy ID khách hàng
//setCustomer_ID()	Cập nhật ID khách hàng
//Customer(...)	Constructor tạo đối tượng khách hàng
//toString()	Trả về chuỗi mô tả khách hàng

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int customer_ID;
	private String customer_Name;
	private String customer_Gender;
	private int customer_Age;
	private String customer_Blood;
	Customer left, right;

	// Getter va Setter
	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getCustomer_Gender() {
		return customer_Gender;
	}

	public void setCustomer_Gender(String customer_Gender) {
		this.customer_Gender = customer_Gender;
	}

	public int getCustomer_Age() {
		return customer_Age;
	}

	public void setCustomer_Age(int customer_Age) {
		this.customer_Age = customer_Age;
	}

	public String getCustomer_Blood() {
		return customer_Blood == null ? "Chua cap nhat" : customer_Blood;
	}

	public void setCustomer_Blood(String customer_Blood) {
		this.customer_Blood = customer_Blood;
	}

	// Constructor 3 tham so
	public Customer(int id, String name, String gender) {
		this(id, name, gender, 0, null);
	}

	// Constructor 4 tham so
	public Customer(int id, String name, String gender, int age) {
		this(id, name, gender, age, null);
	}

	// Constructor 5 tham so
	public Customer(int id, String name, String gender, int age, String blood) {

		this.customer_ID = id;
		this.customer_Name = name;
		this.customer_Gender = gender;
		this.customer_Age = age;
		this.customer_Blood = blood;

		this.left = null;
		this.right = null;
	}

	// Constructor 7 tham so
	public Customer(int id, String name, String gender, int age, String blood, Customer left, Customer right) {

		this(id, name, gender, age, blood);

		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "Customer [customer_ID=" + customer_ID + ", customer_Name=" + customer_Name + ", customer_Gender="
				+ customer_Gender + ", customer_Age=" + customer_Age + ", customer_Blood=" + getCustomer_Blood() + "]";
	}

}

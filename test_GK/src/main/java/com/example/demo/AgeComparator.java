package com.example.demo;

import java.util.Comparator;
//Ví dụ: khách 20 tuổi được xếp trước khách 25 tuổi khi sắp xếp tăng dần

public class AgeComparator implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		return Integer.compare(
					o1.getCustomer_Age(),
					o2.getCustomer_Age()
				);
	}

}

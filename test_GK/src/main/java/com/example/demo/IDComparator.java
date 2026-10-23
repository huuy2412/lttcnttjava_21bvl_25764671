package com.example.demo;

import java.util.Comparator;
//ID nhỏ hơn: trả về số âm.
//ID bằng nhau: trả về 0.
//ID lớn hơn: trả về số dương.

public class IDComparator implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		return Integer.compare(
					o1.getCustomer_ID(),
					o2.getCustomer_ID()
				);
	}
}

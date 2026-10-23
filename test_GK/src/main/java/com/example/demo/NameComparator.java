package com.example.demo;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class NameComparator implements Comparator<Customer> {
	@Override
	public int compare(Customer o1, Customer o2) {
		Collator vi = Collator.getInstance(Locale.forLanguageTag("vi-VN"));

		int kq = vi.compare(
					Main.getTen(o1.getCustomer_Name()), 
					Main.getTen(o2.getCustomer_Name())
				);

		if (kq != 0) {
			return kq;
		}

		kq = vi.compare(o1.getCustomer_Name(), o2.getCustomer_Name());

		if (kq != 0) {
			return kq;
		}

		return Integer.compare(o1.getCustomer_ID(), o2.getCustomer_ID());
	}

}

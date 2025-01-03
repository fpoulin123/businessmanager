package ca.xpertproject.apps.businessmanager.model;

import java.util.Comparator;

public class CustomerIdComparator implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		
		Customer c1 = (Customer) o1;
		Customer c2 = (Customer) o2;
		
		if(c1.id < c2.id) return -1;
		if(c1.id > c2.id) return 1;

		return 0;
	}

}

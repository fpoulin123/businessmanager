package ca.xpertproject.apps.businessmanager.model;

import java.util.Comparator;

import ca.xpertproject.apps.businessmanager.objects.CustomerLight;

public class CustomerLightFullNameComparator implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		
		CustomerLight c1 = (CustomerLight) o1;
		CustomerLight c2 = (CustomerLight) o2;
		
		return c1.getLabel().compareTo(c2.getLabel());
	}

}

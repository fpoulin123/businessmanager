package ca.xpertproject.apps.businessmanager.objects.mappers;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;

public class CustomerMapper {
	
	public static CustomerLight convertForList(Customer customer) {
		CustomerLight customerLight = new CustomerLight();
		
		String fullName = customer.getFirstName() + " " + customer.getLastName();
		
		fullName = fullName.trim();

		customerLight.setLabel(fullName);
		
		customerLight.setValue(customer.getId().toString());

		return customerLight;
	}

}

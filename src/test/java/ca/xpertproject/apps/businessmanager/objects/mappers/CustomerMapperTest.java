package ca.xpertproject.apps.businessmanager.objects.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;

@SpringBootTest
public class CustomerMapperTest {

	EasyRandom easyRandom = new EasyRandom();
	
	@Test
	public void convertForList_testValues() {
		Customer customer = easyRandom.nextObject(Customer.class);
				
		CustomerLight customerLight = CustomerMapper.convertForList(customer);
		
		assertEquals(customer.getId().toString(), customerLight.getValue());
		
		String fullName = customer.getFirstName() + " " + customer.getLastName();
		
		fullName = fullName.trim();
		
		assertEquals(fullName, customerLight.getLabel());
	}
}

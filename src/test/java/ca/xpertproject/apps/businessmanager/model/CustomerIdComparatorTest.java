package ca.xpertproject.apps.businessmanager.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerIdComparatorTest {

	CustomerIdComparator comp = new CustomerIdComparator();
	
	@Test
	public void compare_before() {
		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		
		c1.setId(Long.valueOf(1));
		c2.setId(Long.valueOf(2));
		
		assertEquals(-1, comp.compare(c1, c2)); 
		
		
	}
	
	@Test
	public void compare_after() {
		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		
		c1.setId(Long.valueOf(1));
		c2.setId(Long.valueOf(2));
		
		assertEquals(1, comp.compare(c2, c1)); 
		
		
	}
	
	@Test
	public void compare_equal() {
		
		Customer c1 = new Customer();
		Customer c2 = new Customer();
		
		c1.setId(Long.valueOf(1));
		c2.setId(Long.valueOf(1));
		
		assertEquals(0, comp.compare(c2, c1)); 
		
	}
}

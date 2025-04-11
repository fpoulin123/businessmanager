package ca.xpertproject.apps.businessmanager.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.xpertproject.apps.businessmanager.objects.CustomerLight;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerLightFullNameComparatorTest {
	
	CustomerLightFullNameComparator comp = new CustomerLightFullNameComparator();
	
	@Test
	public void compare_before() {
		CustomerLight c1 = new CustomerLight();
		CustomerLight c2 = new CustomerLight();
		
		c1.setLabel("a");
		c2.setLabel("b");
		
		assertEquals(-1, comp.compare(c1, c2));
		
	}
	
	@Test
	public void compare_after() {
		CustomerLight c1 = new CustomerLight();
		CustomerLight c2 = new CustomerLight();
		
		c1.setLabel("a");
		c2.setLabel("b");
		
		assertEquals(1, comp.compare(c2, c1));
		
		
		
	}
	
	@Test
	public void compare_equal() {
		
		CustomerLight c1 = new CustomerLight();
		CustomerLight c2 = new CustomerLight();
		
		c1.setLabel("a");
		c2.setLabel("a");
		
		assertEquals(0, comp.compare(c1, c2));
		
		
	}

}

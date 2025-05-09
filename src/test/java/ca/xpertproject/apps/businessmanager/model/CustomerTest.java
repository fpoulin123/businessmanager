package ca.xpertproject.apps.businessmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTest {
	
	EasyRandom easyRandom = new EasyRandom();
	
	StringRandomizer stringRdmz = new StringRandomizer(20);
	
	Customer customer = new Customer();
	
	@Test
	public void setId_test() {
		customer.setId(Long.valueOf(1));
		assertEquals(Long.valueOf(1), customer.getId());	
	}
	
	@Test
	public void setAddress_test() {
		String address = stringRdmz.getRandomValue();
		customer.setAddress(address);
		
		assertEquals(address, customer.getAddress());
	}
	
	@Test
	public void setBarcodeValue_test() {
		String barcodeValue = stringRdmz.getRandomValue();
		customer.setBarcodeValue(barcodeValue);
		
		assertEquals(barcodeValue, customer.getBarcodeValue());
	}
	
	@Test
	public void setCity_test() {
		String city = stringRdmz.getRandomValue();
		customer.setCity(city);
		
		assertEquals(city, customer.getCity());
	}
	
	@Test
	public void setEmail_test() {
		String email = stringRdmz.getRandomValue();
		customer.setEmail(email);
		
		assertEquals(email, customer.getEmail());
	}
	
	@Test
	public void setFirstName_test() {
		String firstName = stringRdmz.getRandomValue();
		customer.setFirstName(firstName);
		
		assertEquals(firstName, customer.getFirstName());
	}
	
	@Test
	public void setLastName_test() {
		String lastName = stringRdmz.getRandomValue();
		customer.setLastName(lastName);
		
		assertEquals(lastName, customer.getLastName());
	}
	
	@Test
	public void setPhoneNumber_test() {
		String phoneNumber = stringRdmz.getRandomValue();
		customer.setPhoneNumber(phoneNumber);
		
		assertEquals(phoneNumber, customer.getPhoneNumber());
	}
	
	@Test
	public void setPicture_test() {
		String picture = stringRdmz.getRandomValue();
		customer.setPicture(picture);
		
		assertEquals(picture, customer.getPicture());
	}
	
	@Test
	public void setSubscriptionList(){
		List<Subscription> subscriptions = easyRandom.objects(Subscription.class, 3).collect(Collectors.toList());
		customer.setSubscriptions(subscriptions);
		
		assertEquals(subscriptions, customer.getSubscriptions());
	}
	
	@Test
	public void constructorFull_test() throws ParseException {
		
			String address = stringRdmz.getRandomValue();
			
			String barcodeValue = stringRdmz.getRandomValue();
			
			String city = stringRdmz.getRandomValue();
			
			String email = stringRdmz.getRandomValue();
			
			String firstName = stringRdmz.getRandomValue();
			
			String lastName = stringRdmz.getRandomValue();
			
			String phoneNumber = stringRdmz.getRandomValue();
			
			String picture = stringRdmz.getRandomValue();
			
			List<Subscription> subscriptions = easyRandom.objects(Subscription.class, 3).collect(Collectors.toList());
			
			String title = stringRdmz.getRandomValue();
			String level = stringRdmz.getRandomValue();
			Integer height = 180;
			Integer weight = 178;
			Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
			Customer customerFull = new Customer(
					Long.valueOf(1), 
					firstName, 
					lastName, 
					address, 
					city, 
					phoneNumber, 
					email, 
					picture, 
					barcodeValue, 
					subscriptions,
					title, 
					level, 
					height, 
					weight, 
					birthdate, 
					false);
			
			assertEquals(Long.valueOf(1), customerFull.getId());
			assertEquals(address, customerFull.getAddress());
			assertEquals(barcodeValue, customerFull.getBarcodeValue());
			assertEquals(city, customerFull.getCity());
			assertEquals(email, customerFull.getEmail());
			assertEquals(firstName, customerFull.getFirstName());
			assertEquals(lastName, customerFull.getLastName());
			assertEquals(phoneNumber, customerFull.getPhoneNumber());
			assertEquals(picture, customerFull.getPicture());
			assertEquals(subscriptions, customerFull.getSubscriptions());
	}
	
	
	@Test
	public void toCsvString_testValue() throws ParseException {
		
		String address = stringRdmz.getRandomValue();
		
		String barcodeValue = stringRdmz.getRandomValue();
		
		String city = stringRdmz.getRandomValue();
		
		String email = stringRdmz.getRandomValue();
		
		String firstName = stringRdmz.getRandomValue();
		
		String lastName = stringRdmz.getRandomValue();
		
		String phoneNumber = stringRdmz.getRandomValue();
		
		String picture = stringRdmz.getRandomValue();
		
		List<Subscription> subscriptions = easyRandom.objects(Subscription.class, 3).collect(Collectors.toList());

		String title = stringRdmz.getRandomValue();
		String level = stringRdmz.getRandomValue();
		Integer height = 180;
		Integer weight = 178;
		Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
		Customer customerFull = new Customer(
				Long.valueOf(1), 
				firstName, 
				lastName, 
				address, 
				city, 
				phoneNumber, 
				email, 
				picture, 
				barcodeValue, 
				subscriptions,
				title, 
				level, 
				height, 
				weight, 
				birthdate, 
				false);
		
		
		String csvString = customerFull.toCsvString();
		
		String expected = String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				Long.valueOf(1), 
				firstName, 
				lastName, 
				address, 
				city, 
				phoneNumber, 
				email);
		
		
		assertEquals(expected, csvString);
	}
	
	
	
}

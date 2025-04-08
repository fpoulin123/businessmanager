package ca.xpertproject.apps.businessmanager.objects.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jeasy.random.EasyRandom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;


@SpringBootTest
public class SubscriptionMapperTest {
	
	EasyRandom easyRandom = new EasyRandom();
	SubscriptionMapper mapper = new SubscriptionMapper();
	
	@Test
	public void convert_testValues() {
		
		
		Customer customer = easyRandom.nextObject(Customer.class);
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		subscription.setCustomer(customer);
		subscription.setCustomerId(customer.getId());
		
		
		
		SubscriptionExt subscriptionExt = mapper.convert(subscription);
		
		assertEquals(subscription.getId(), subscriptionExt.getId());
		assertEquals(subscription.getAmount(), subscriptionExt.getAmount());
		assertEquals(subscription.getDuration(), subscriptionExt.getDuration());
		assertEquals(subscription.getSubscriptionDate(), subscriptionExt.getSubscriptionDate());
		assertEquals(subscription.getCustomerId(), subscriptionExt.getCustomerId());
		assertEquals(subscription.getCustomer().getFirstName(), subscriptionExt.getCustomerFirstname());
		assertEquals(subscription.getCustomer().getLastName(), subscriptionExt.getCustomerLastname());
		
	}
	
	
	@Test
	public void isValid_true() throws ParseException {
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		subscription.setSubscriptionDate(new Date());
		subscription.setDuration(30);
		
		SubscriptionExt subscriptionExt = mapper.convert(subscription);
		
		assertTrue(subscriptionExt.getIsValid());
	}
	
	@Test
	public void isValid_false() throws ParseException {
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date subscriptionDate = sdf.parse("2024-01-01");
		
		
		subscription.setSubscriptionDate(subscriptionDate);
		subscription.setDuration(30);
		
		SubscriptionExt subscriptionExt = mapper.convert(subscription);
		
		assertFalse(subscriptionExt.getIsValid());
	}
	
	
	
	@Test
	public void isValid_false_nullSubscriptionDate() throws ParseException {
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		subscription.setSubscriptionDate(null);
		subscription.setDuration(30);
		
		SubscriptionExt subscriptionExt = mapper.convert(subscription);
		
		assertFalse(subscriptionExt.getIsValid());
	}
	
	@Test
	public void isValid_false_nullDuration() throws ParseException {
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		subscription.setSubscriptionDate(new Date());
		subscription.setDuration(null);
		
		SubscriptionExt subscriptionExt = mapper.convert(subscription);
		
		assertFalse(subscriptionExt.getIsValid());
	}

}

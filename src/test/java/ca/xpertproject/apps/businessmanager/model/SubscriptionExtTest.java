package ca.xpertproject.apps.businessmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@SpringBootTest
public class SubscriptionExtTest {
	
	EasyRandom easyRandom = new EasyRandom();
	
	SubscriptionExt subscriptionExt = new SubscriptionExt();
	
	Long id = Long.valueOf(1);
	
	Long customerId = Long.valueOf(2);
	
	Customer customer = easyRandom.nextObject(Customer.class);
	
	Integer duration = easyRandom.nextInt();
	
	Boolean taekwondo = easyRandom.nextBoolean();

	Boolean kickboxing = easyRandom.nextBoolean();
	
	Boolean taekibodo = easyRandom.nextBoolean();
	
	Double amount = easyRandom.nextDouble();
	
	Date subscriptionDate = easyRandom.nextObject(Date.class);
	
	Date expirationDate = easyRandom.nextObject(Date.class);
	
	Boolean isValid = true;
	
	List<Payment> paymentList = easyRandom.objects(Payment.class, 3).collect(Collectors.toList());
	
	@Test
	public void customer_constructorFull() {
		SubscriptionExt subscriptionFull = new SubscriptionExt(id, customerId, customer.getFirstName(), customer.getLastName(), duration, taekwondo, kickboxing, taekibodo, amount, subscriptionDate, expirationDate, isValid);
		assertEquals(this.id, subscriptionFull.getId());
		assertEquals(this.customerId, subscriptionFull.getCustomerId());
		assertEquals(this.customer.getFirstName(), subscriptionFull.getCustomerFirstname());
		assertEquals(this.customer.getLastName(), subscriptionFull.getCustomerLastname());
		assertEquals(this.duration, subscriptionFull.getDuration());
		assertEquals(this.taekwondo, subscriptionFull.getTaekwondo());
		assertEquals(this.kickboxing, subscriptionFull.getKickboxing());
		assertEquals(this.taekibodo, subscriptionFull.getTaekibodo());
		assertEquals(this.amount, subscriptionFull.getAmount());
		assertEquals(this.subscriptionDate, subscriptionFull.getSubscriptionDate());
		
		
	}
	
	@Test
	public void customer_constructor() {
		Subscription subscriptionPart = new Subscription(id, customerId, customer, duration, taekwondo, kickboxing, taekibodo, amount, subscriptionDate);
		assertEquals(this.id, subscriptionPart.getId());
		assertEquals(this.customerId, subscriptionPart.getCustomerId());
		assertEquals(this.customer, subscriptionPart.getCustomer());
		assertEquals(this.duration, subscriptionPart.getDuration());
		assertEquals(this.taekwondo, subscriptionPart.isTaekwondo());
		assertEquals(this.kickboxing, subscriptionPart.isKickboxing());
		assertEquals(this.taekibodo, subscriptionPart.isTaekibodo());
		assertEquals(this.amount, subscriptionPart.getAmount());
		assertEquals(this.subscriptionDate, subscriptionPart.getSubscriptionDate());
		
		
	}
	
	@Test
	public void setValues_test() {
		
		subscriptionExt.setId(id);
		subscriptionExt.setAmount(amount);
		subscriptionExt.setCustomerFirstname(customer.firstName);
		subscriptionExt.setCustomerLastname(customer.lastName);
		subscriptionExt.setCustomerId(customer.getId());
		subscriptionExt.setDuration(duration);
		subscriptionExt.setKickboxing(kickboxing);
		subscriptionExt.setTaekibodo(taekibodo);
		subscriptionExt.setTaekwondo(taekwondo);
		subscriptionExt.setSubscriptionDate(subscriptionDate);
		subscriptionExt.setIsValid(true);
		subscriptionExt.setExpirationDate(expirationDate);
		subscriptionExt.setPayed(true);
		subscriptionExt.setBalance(234.56);
		
		assertEquals(this.id, subscriptionExt.getId());
		assertEquals(this.customer.getId(), subscriptionExt.getCustomerId());
		assertEquals(this.customer.getFirstName(), subscriptionExt.getCustomerFirstname());
		assertEquals(this.customer.getLastName(), subscriptionExt.getCustomerLastname());
		assertEquals(this.duration, subscriptionExt.getDuration());
		assertEquals(this.taekwondo, subscriptionExt.getTaekwondo());
		assertEquals(this.kickboxing, subscriptionExt.getKickboxing());
		assertEquals(this.taekibodo, subscriptionExt.getTaekibodo());
		assertEquals(this.amount, subscriptionExt.getAmount());
		assertEquals(this.subscriptionDate, subscriptionExt.getSubscriptionDate());
		assertEquals(true, subscriptionExt.getIsValid());
		assertEquals(expirationDate, subscriptionExt.getExpirationDate());
		assertEquals(true, subscriptionExt.getPayed());
		assertEquals(234.56, subscriptionExt.getBalance());
	}
	
			

}

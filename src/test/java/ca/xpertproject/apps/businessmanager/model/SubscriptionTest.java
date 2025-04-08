package ca.xpertproject.apps.businessmanager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.randomizers.text.StringRandomizer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@SpringBootTest
public class SubscriptionTest {
	
	EasyRandom easyRandom = new EasyRandom();
	
	Subscription subscription = new Subscription();
	
	Long id = Long.valueOf(1);
	
	Long customerId = Long.valueOf(2);
	
	Customer customer = easyRandom.nextObject(Customer.class);
	
	Integer duration = easyRandom.nextInt();
	
	Boolean taekwondo = easyRandom.nextBoolean();

	Boolean kickboxing = easyRandom.nextBoolean();
	
	Boolean taekibodo = easyRandom.nextBoolean();
	
	Double amount = easyRandom.nextDouble();
	
	Date subscriptionDate = easyRandom.nextObject(Date.class);
	
	List<Payment> paymentList = easyRandom.objects(Payment.class, 3).collect(Collectors.toList());
	
	@Test
	public void customer_constructorFull() {
		Subscription subscriptionFull = new Subscription(id, customerId, customer, duration, taekwondo, kickboxing, taekibodo, amount, subscriptionDate, paymentList);
		assertEquals(this.id, subscriptionFull.getId());
		assertEquals(this.customerId, subscriptionFull.getCustomerId());
		assertEquals(this.customer, subscriptionFull.getCustomer());
		assertEquals(this.duration, subscriptionFull.getDuration());
		assertEquals(this.taekwondo, subscriptionFull.isTaekwondo());
		assertEquals(this.kickboxing, subscriptionFull.isKickboxing());
		assertEquals(this.taekibodo, subscriptionFull.isTaekibodo());
		assertEquals(this.amount, subscriptionFull.getAmount());
		assertEquals(this.subscriptionDate, subscriptionFull.getSubscriptionDate());
		assertEquals(this.paymentList,subscriptionFull.getPaymentList());
		
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
		
		subscription.setId(id);
		subscription.setAmount(amount);
		subscription.setCustomer(customer);
		subscription.setCustomerId(customer.getId());
		subscription.setDuration(duration);
		subscription.setKickboxing(kickboxing);
		subscription.setTaekibodo(taekibodo);
		subscription.setTaekwondo(taekwondo);
		subscription.setSubscriptionDate(subscriptionDate);
		subscription.setPaymentList(paymentList);
		assertEquals(this.id, subscription.getId());
		assertEquals(this.customer.getId(), subscription.getCustomerId());
		assertEquals(this.customer, subscription.getCustomer());
		assertEquals(this.duration, subscription.getDuration());
		assertEquals(this.taekwondo, subscription.isTaekwondo());
		assertEquals(this.kickboxing, subscription.isKickboxing());
		assertEquals(this.taekibodo, subscription.isTaekibodo());
		assertEquals(this.amount, subscription.getAmount());
		assertEquals(this.subscriptionDate, subscription.getSubscriptionDate());
		assertEquals(this.paymentList,subscription.getPaymentList());
		
	}
	
	@Test
	public void toCsvString_test(){
		
		subscription.setId(id);
		subscription.setAmount(amount);
		subscription.setCustomer(customer);
		subscription.setCustomerId(customer.getId());
		subscription.setDuration(duration);
		subscription.setKickboxing(kickboxing);
		subscription.setTaekibodo(taekibodo);
		subscription.setTaekwondo(taekwondo);
		subscription.setSubscriptionDate(subscriptionDate);
		subscription.setPaymentList(paymentList);
		
		String expected = String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id, 
				this.customer.firstName, 
				this.customer.lastName, 
				this.duration, 
				(this.taekwondo)?"Taekwondo":"", 
				(this.kickboxing)?"Kickboxing":"",
				(this.taekibodo)?"Taekibodo":"", 
				this.amount, 
				this.subscriptionDate);
		
		assertEquals(expected, subscription.toCsvString());
	}
			

}

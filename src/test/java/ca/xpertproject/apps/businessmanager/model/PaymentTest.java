package ca.xpertproject.apps.businessmanager.model;

import java.util.Date;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentTest {

	EasyRandom easyRandom = new EasyRandom();
	
	Long id = Long.valueOf(1);
	
	Double amount = easyRandom.nextDouble();
	
	Date paymentDate = easyRandom.nextObject(Date.class);
	
	Subscription subscription = easyRandom.nextObject(Subscription.class);
	
	Long subscriptionId = subscription.getId();
	
	@Test
	public void payment_constructorFull() {
		Payment payment = new Payment(id, subscriptionId, amount, paymentDate, subscription);
		
		assertEquals(id, payment.getId());
		assertEquals(subscriptionId, payment.getSubscriptionId());
		assertEquals(amount, payment.getAmount());
		assertEquals(paymentDate, payment.getPaymentDate());
		assertEquals(subscription, payment.getSubscription());
	}
	
	@Test
	public void toCsvString_vali() {
		Payment payment = new Payment(id, subscriptionId, amount, paymentDate, subscription);
		
		String expected = String.format("\"%s\";\"%s\";\"%s\";\"%s\";\"%s\";\"%s\"", 
				this.id,
				this.subscriptionId,
				this.subscription.customer.firstName,
				this.subscription.customer.lastName,
				this.amount,
				this.paymentDate
			);
		
		assertEquals(expected, payment.toCsvString());
		
	}
}

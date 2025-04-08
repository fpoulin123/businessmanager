package ca.xpertproject.apps.businessmanager.objects.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.objects.PaymentExt;

@SpringBootTest
public class PaymentMapperTest {
	
	EasyRandom easyRandom = new EasyRandom();
	
	@Test
	public void convert_testValues() {
		
		
		Payment payment = easyRandom.nextObject(Payment.class);
		Customer customer = easyRandom.nextObject(Customer.class);
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		subscription.setCustomer(customer);
		
		payment.setSubscription(subscription);
		payment.setSubscriptionId(subscription.getId());
		
		PaymentExt paymentExt = PaymentMapper.convert(payment);
		
		assertEquals(payment.getId(), paymentExt.getId());
		assertEquals(payment.getAmount(), paymentExt.getAmount());
		assertEquals(payment.getPaymentDate(), paymentExt.getPaymentDate());
		assertEquals(payment.getSubscription().getCustomer().getFirstName(), paymentExt.getCustomerFirstName());
		assertEquals(payment.getSubscription().getCustomer().getLastName(), paymentExt.getCustomerLastName());
	
	}

}

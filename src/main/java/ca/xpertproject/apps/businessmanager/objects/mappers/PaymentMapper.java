package ca.xpertproject.apps.businessmanager.objects.mappers;

import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.objects.PaymentExt;

public class PaymentMapper {
	
	public static PaymentExt convert(Payment payment) {
		PaymentExt paymentExt = new PaymentExt();
		
		paymentExt.setId(payment.getId());
		paymentExt.setSubscriptionId(payment.getSubscriptionId());
		paymentExt.setAmount(payment.getAmount());
		paymentExt.setPaymentDate(payment.getPaymentDate());
		Subscription subscription = payment.getSubscription();
		paymentExt.setCustomerFirstName(subscription.customer.firstName);
		paymentExt.setCustomerLastName(subscription.customer.lastName);
		
		return paymentExt;
	}

}

package ca.xpertproject.apps.businessmanager.objects.mappers;

import java.util.Date;

import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;

public class SubscriptionMapper {
	
	private static final long DAYINMILLIS = 24*3600*1000;
	
	public SubscriptionExt convert(Subscription subscription) {
		
		return GenericBuilder.of(SubscriptionExt::new)
				.with(SubscriptionExt::setId,subscription.getId())
				.with(SubscriptionExt::setCustomerId, subscription.getCustomerId())
				.with(SubscriptionExt::setCustomerFirstname, subscription.getCustomer().getFirstName())
				.with(SubscriptionExt::setCustomerLastname, subscription.getCustomer().getLastName())
				.with(SubscriptionExt::setDuration, subscription.getDuration())
				.with(SubscriptionExt::setTaekwondo, subscription.isTaekwondo())
				.with(SubscriptionExt::setKickboxing, subscription.isKickboxing())
				.with(SubscriptionExt::setTaekibodo, subscription.isTaekibodo())
				.with(SubscriptionExt::setAmount, subscription.getAmount())
				.with(SubscriptionExt::setSubscriptionDate, subscription.getSubscriptionDate())
				.with(SubscriptionExt::setExpirationDate, getExpirationDate(subscription))
				.with(SubscriptionExt::setIsValid, isValid(subscription))
				.with(SubscriptionExt::setPayed, getPayed(subscription))
				.with(SubscriptionExt::setBalance, getBalance(subscription))
				.build();
				

	}

	private Double getBalance(Subscription subscription) {
		// TODO Auto-generated method stub
		Double totalAmountPayed = 0.0;
		for (Payment payment : subscription.paymentList) {
			totalAmountPayed = totalAmountPayed + payment.amount;
		}

		Double leftToPay = subscription.amount - totalAmountPayed;

		return leftToPay;
	}

	private Boolean getPayed(Subscription subscription) {
		// TODO Auto-generated method stub
		return getBalance(subscription).doubleValue()==0;
	}

	private Boolean isValid(Subscription subscription) {
		if(getExpirationDate(subscription)==null) {
			return false;
		}
		
		return getExpirationDate(subscription).after(new Date());
	}

	private Date getExpirationDate(Subscription subscription) {
		
		if(subscription.getSubscriptionDate()==null) {
			return null;
		}
		
		if(subscription.getDuration()==null) {
			return null;
		}
				
		long subscriptionDateTime = subscription.getSubscriptionDate().getTime();
		
		long durationLong = Integer.toUnsignedLong(subscription.getDuration());
			
		long durationDateTime = durationLong*DAYINMILLIS;
		
		long expirationDateTime = subscriptionDateTime + durationDateTime;
		
		return new Date(expirationDateTime);
	}

}

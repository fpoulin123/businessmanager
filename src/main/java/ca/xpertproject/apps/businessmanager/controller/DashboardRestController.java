package ca.xpertproject.apps.businessmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;

@RestController
public class DashboardRestController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@GetMapping("paymentsData")
	public List<Double> getPaymentsData(){
		List<Double> paymentsData = new ArrayList<>();
		
		Double totalPayments = 0.0;
		Double totalSubscriptions = 0.0;
		List<Payment> payments = paymentRepository.findAll();
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		
		for (Subscription subscription : subscriptions) {
			totalSubscriptions += subscription.amount;
		}
		
		for (Payment payment : payments) {
			totalPayments += payment.amount;
		}
		
		Double totalDue = totalSubscriptions - totalPayments;
		
		paymentsData.add(totalPayments);
		paymentsData.add(totalDue);
		
		return paymentsData;
	}

}

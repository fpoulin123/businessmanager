package ca.xpertproject.apps.businessmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.dashboard.IWidgetsDataFeeder;
import ca.xpertproject.apps.businessmanager.dashboard.WidgetsDataFeeder;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;

@RestController
public class DashboardRestController {
		
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	IWidgetsDataFeeder dataFeeder = new WidgetsDataFeeder();
	
	@GetMapping("paymentsData")
	public List<Double> getPaymentsData(){
		return dataFeeder.getPaymentsData(paymentRepository, subscriptionRepository);
	}
	
	@GetMapping("validSubscriptionNumber")
	public Integer getValidSubscriptionNumber() {
		
		return dataFeeder.getValidSubscriptionNumber(subscriptionRepository);
	}
	
	@GetMapping("unpaidSubs")
	public Integer getPartialOrUnpaidSubscriptions() {
		 return dataFeeder.getPartialOrUnpaidSubscriptions(subscriptionRepository);
	}
	
	@GetMapping("monthlyCA")
	public List<MonthlyCA> getCAByMonth(){
		return dataFeeder.getCAByMonth(paymentRepository, subscriptionRepository);
	}
	
	@GetMapping("monthlySubs")
	public List<MonthlySubNbr> getSubsByMonth(){
		
		return dataFeeder.getSubsByMonth(subscriptionRepository);
	}

}

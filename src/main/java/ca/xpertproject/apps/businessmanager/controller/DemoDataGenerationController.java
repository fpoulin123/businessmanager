package ca.xpertproject.apps.businessmanager.controller;


import java.awt.desktop.ScreenSleepEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;

@Controller
public class DemoDataGenerationController {
	
	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventAttendeeRepository eventAttendeeRepository;
	
	@GetMapping("/generateDemoSubscriptionData")
	public String generateDemoSubscriptionData() throws ParseException {

		List<Customer> customerList = customerRepository.findAll();

		for (Customer customer : customerList) {

			Random random = new Random();
			int subsType = random.nextInt(1, 4);
			Subscription subscription = new Subscription();
			subscription.setCustomer(customer);
			subscription.setTaekwondo(random.nextBoolean());
			subscription.setTaekibodo(random.nextBoolean());
			subscription.setKickboxing(random.nextBoolean());
			if (subsType == 1) {
				subscription.setDuration(30);
				subscription.setAmount(200.0);
			} else if (subsType == 2) {
				subscription.setDuration(90);
				subscription.setAmount(450.0);
			} else if (subsType == 3) {
				subscription.setDuration(365);
				subscription.setAmount(1200.0);
			}

			int year = random.nextInt(2000, 2026);
			int month = random.nextInt(1, 13);
			int day = random.nextInt(1, 31);

			String dateStr = year + "-" + month + "-" + day;

			Date subDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			subscription.setSubscriptionDate(subDate);

			subscriptionRepository.save(subscription);

		}

		return "redirect:allSubscriptions";
	}

	@GetMapping("/generateDemoPaymentData")
	public String generateDemoPaymentData() throws ParseException {

		List<Subscription> subscriptions = subscriptionRepository.findAll();

		for (Subscription subscription : subscriptions) {
			Random random = new Random();
			boolean createPayment = random.nextBoolean();
			System.out.println("CREATE PAYMENT : " + createPayment);
			if (createPayment) {
				boolean paymentFull = random.nextBoolean();
				Payment payment = new Payment();
				payment.setSubscription(subscription);
				payment.setPaymentDate(subscription.getSubscriptionDate());
				if (paymentFull) {
					payment.setAmount(subscription.getAmount());
				} else {
					payment.setAmount(random.nextDouble(0.0, subscription.amount + 1.0));
				}
				paymentRepository.save(payment);
			}

		}

		return "redirect:allPayments";

	}
	
	@GetMapping("/generateDemoEventData")
	public String generateDemoEventData() {
		
		Event event1 = new Event();
		event1.setEventName("Compétition Kickboxing");
		event1.setEventType("Compétition");
		event1.setAmount(50.0);
		
		Event event2 = new Event();
		event2.setEventName("Compétition Taekwondo");
		event2.setEventType("Compétition");
		event2.setAmount(45.0);
		
		Event event3 = new Event();
		event3.setEventName("Examen - passage ceinture Taekwondo");
		event3.setEventType("Examen - passage de grade");
		
		
		return "redirect:/allEvents";
	}

}

package ca.xpertproject.apps.businessmanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.SubscriptionMapper;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class SubscriptionController {
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/allSubscriptions")
	public String getAllPayments(Model model) {
		
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		
		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub-> new SubscriptionMapper().convert(sub)).collect(Collectors.toList());
		
		model.addAttribute("subscriptions", subscriptionExtList);
		
		return "subscriptions";
	}
	
	@GetMapping("/subscription")
	public String getSubscription(@RequestParam(required = true) Long id, Model model) {
		
		Subscription subscription = subscriptionRepository.findById(id);
		
		model.addAttribute("subscription", subscription);
		
		long subscriptionDateTime = subscription.getSubscriptionDate().getTime();
		
		int duration = subscription.getDuration();
		
		long durationLong = Integer.toUnsignedLong(duration);
					
		long dayInMillis = 24*3600*1000;
		
		long durationDateTime = durationLong*dayInMillis;
		
		System.out.println("Duration : " + duration);
		
		long expirationDateTime = subscriptionDateTime + durationDateTime;
		
		Date expirationDate = new Date(expirationDateTime);
		
		boolean subscriptionValid = expirationDate.after(new Date());
		
		model.addAttribute("expirationDate", expirationDate);
		
		model.addAttribute("subscriptionValid", subscriptionValid);
		
		return "subscription";
	}
	
	@GetMapping("/addSubscription")
	public String getAddSubscription(@RequestParam(required = false) Long customerid, Model model) {
		
		model.addAttribute("customerid", customerid);
		
		return "createSubscriptionform";
	}
	
	@PostMapping("/addSubscription")
	public String addSubscription(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		boolean taekwondo = "on".equals(body.get("taekwondo"))?true:false;
		boolean kickboxing = "on".equals(body.get("kickboxing"))?true:false;
		boolean taekibodo = "on".equals(body.get("taekibodo"))?true:false;
		
		String subscriptionDateValue = body.get("subscriptionDate");
		Date subscriptionDate = null;
		
		if(subscriptionDateValue!=null &&  !(subscriptionDateValue.isEmpty())) {
			subscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("subscriptionDate"));
		}
		
		Customer customer = customerRepository.findById(Long.parseLong(body.get("customerid")));
		
		Subscription subscription = GenericBuilder.of(Subscription::new)
				.with(Subscription::setCustomer, customer)
				.with(Subscription::setDuration,Integer.parseInt(body.get("duration")))
				.with(Subscription::setTaekwondo,taekwondo)
				.with(Subscription::setKickboxing,kickboxing)
				.with(Subscription::setTaekibodo, taekibodo)
				.with(Subscription::setAmount,Double.parseDouble(body.get("amount")))
				.with(Subscription::setSubscriptionDate,subscriptionDate)
				.build();
				
		subscription = subscriptionRepository.save(subscription);
				
		return "redirect:./subscription?id=" + subscription.id;
	}
	
	@GetMapping("/modifySubscription")
	public String modifySubscriptiont(@RequestParam(required=true) Long id, Model model) {
		Subscription subscription = subscriptionRepository.findById(id);
		
		model.addAttribute("subscription", subscription);
		
		return "modifySubscriptionForm";
	}
	
	@PostMapping("/modifySubscription")
	public String modifySubscription(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		body.forEach((k,v)-> System.out.println(k + ":" + v));
		
		boolean taekwondo = "on".equals(body.get("taekwondo"))?true:false;
		boolean kickboxing = "on".equals(body.get("kickboxing"))?true:false;
		boolean taekibodo = "on".equals(body.get("taekibodo"))?true:false;
		
		String subscriptionDateValue = body.get("subscriptionDate");
		Date subscriptionDate = null;
		
		if(subscriptionDateValue!=null &&  !(subscriptionDateValue.isEmpty())) {
			subscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("subscriptionDate"));
		}
		
		Subscription subscription = subscriptionRepository.findById(Long.parseLong(body.get("id")));
				
		Customer customer = customerRepository.findById(Long.parseLong(body.get("customerid")));
				
		subscription = GenericBuilder.of(Subscription::new)
				.with(Subscription::setId, subscription.getId())
				.with(Subscription::setCustomer, customer)
				.with(Subscription::setDuration,Integer.parseInt(body.get("duration")))
				.with(Subscription::setTaekwondo,taekwondo)
				.with(Subscription::setKickboxing,kickboxing)
				.with(Subscription::setTaekibodo, taekibodo)
				.with(Subscription::setAmount,Double.parseDouble(body.get("amount")))
				.with(Subscription::setSubscriptionDate,subscriptionDate)
				.build();
		
		subscription = subscriptionRepository.save(subscription);
		
		return "redirect:./subscription?id=" + subscription.id;
	}

}

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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.SubscriptionMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.HttpServletResponse;
import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;


@Controller
public class SubscriptionController {
	
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	@GetMapping("/allSubscriptions")
	public String getAllSubscriptions(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		
		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub-> new SubscriptionMapper().convert(sub)).collect(Collectors.toList());
		
		model.addAttribute("subscriptions", subscriptionExtList);
		
		return "subscriptions";
	}
	
	@GetMapping("/validSubscriptions")
	public String getValidSubscriptions(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model) {
		
		System.out.println("Get all valid subscriptions.");
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		
		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub-> new SubscriptionMapper().convert(sub)).filter(t -> t.getIsValid()).collect(Collectors.toList());
		
		model.addAttribute("subscriptions", subscriptionExtList);
		
		return "subscriptions";
	}
	
	@GetMapping("/subscriptions")
	public String getSubscriptions(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = true) Long customerId, Model model) {
		
		System.out.println("Get subscriptions for customer " + customerId);
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		
		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub-> new SubscriptionMapper().convert(sub)).filter(t -> customerId.equals(t.getCustomerId())).collect(Collectors.toList());
		
		model.addAttribute("subscriptions", subscriptionExtList);
		
		return "subscriptions";
	}
	
	@GetMapping("/subscription")
	public String getSubscription(@CookieValue(value =MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = true) Long id, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
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
		
		List<Payment> payments = subscription.paymentList;
		
		model.addAttribute("expirationDate", expirationDate);
		
		model.addAttribute("subscriptionValid", subscriptionValid);
		
		model.addAttribute("payments", payments);
		
		Double totalAmountPayed = 0.0;
		
		for (Payment payment : payments) {
			totalAmountPayed = totalAmountPayed + payment.amount;
		}
		
		Double leftToPay = subscription.amount - totalAmountPayed;
		
		model.addAttribute("totalPayed", totalAmountPayed);
		model.addAttribute("leftToPay", leftToPay);
		
		return "subscription";
	}
	
	@GetMapping("/addSubscription")
	public String getAddSubscription(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false) Long customerid, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		List<CustomerLight> customerList = (List<CustomerLight>) customerRepository.findAll().stream().map(customer-> convertForList(customer)).collect(Collectors.toList());
		
		//customerList.forEach(c-> System.out.println(c.getFullname() + ": " + c.getId()));
		
		model.addAttribute("customerList", customerList);
		
		model.addAttribute("customerid", customerid);
		
		return "createSubscriptionform";
	}
	
	public CustomerLight convertForList(Customer customer) {
		CustomerLight customerLight = new CustomerLight();

		customerLight.setFullname(customer.firstName + " " + customer.lastName);
		
		customerLight.setId(customer.getId());

		return customerLight;
	}
	
	@PostMapping("/addSubscription")
	public String addSubscription(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) throws ParseException {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
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
	public String modifySubscriptiont(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required=true) Long id, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Subscription subscription = subscriptionRepository.findById(id);
		
		model.addAttribute("subscription", subscription);
		
		return "modifySubscriptionForm";
	}
	
	@PostMapping("/modifySubscription")
	public String modifySubscription(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) throws ParseException {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
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

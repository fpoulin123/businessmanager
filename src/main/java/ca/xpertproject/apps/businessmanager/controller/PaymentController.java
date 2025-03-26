package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.PaymentExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.PaymentMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PaymentController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	@GetMapping("allPayments")
	public String getAllPayments(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, Model model) throws AuthenticationException {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		List<Payment> payments = paymentRepository.findAll();
		
		List<PaymentExt> paymentExtList = payments.stream().map(pay-> PaymentMapper.convert(pay)).collect(Collectors.toList());
		
		model.addAttribute("payments", paymentExtList);
		
		return "payments";
	}
	
	@GetMapping("payments")
	public String getPayments(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = true) Long subscriptionId ,Model model) {
		
		List<Payment> payments = paymentRepository.findBySubscriptionId(subscriptionId);
		
		model.addAttribute("payments", payments);
		
		return "payments";
	}
	
	@GetMapping("addPayment")
	public String addPayment(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = true) Long subscriptionId ,Model model) {
		
		model.addAttribute("subscriptionId", subscriptionId);
		
		return "createPaymentForm";
	}
	
	@PostMapping("addPayment")
	public String addPayment(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) throws ParseException {
		
		Iterator<Entry<String, String>> prms = body.entrySet().iterator();
		
		while (prms.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) prms.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		Payment payment = new Payment();
		
		String paymentDateValue = body.get("paymentDate");
		Date paymentDate = null;
		
		if(paymentDateValue!=null &&  !(paymentDateValue.isEmpty())) {
			paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDateValue);
		}
		
		Long subscriptionId = null;
		String subscriptionIdStr = body.get("subscriptionId");
		subscriptionId = Long.parseLong(subscriptionIdStr);
		
		Subscription subscription = subscriptionRepository.findById(subscriptionId);
		
		payment.setSubscription(subscription);
		payment.setAmount(Double.valueOf(body.get("amount")));
		payment.setPaymentDate(paymentDate);;
		
		paymentRepository.save(payment);
		 
		return "redirect:./subscription?id=" + subscriptionId;
	}

}

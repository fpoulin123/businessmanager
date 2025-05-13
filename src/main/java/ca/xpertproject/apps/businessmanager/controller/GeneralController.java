package ca.xpertproject.apps.businessmanager.controller;

import java.net.URI;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.StringComparator;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class GeneralController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	
	@GetMapping("/home")
	public String getEvent(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model, HttpServletRequest httpRequest) throws AuthenticationException {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return "noaccess";
		
		MemberUtils memberUtils = new MemberUtils();
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model)) {
			System.out.println("Check cookie failed");
			
			return "redirect:signin";
		};
				
		return "home";
		
	}
	
	@GetMapping("/monthlyRevenue")
	public String getMonthlyRevenue(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, @RequestParam(required = false) String year, Model model, HttpServletRequest httpRequest) throws AuthenticationException {
		
		MemberUtils memberUtils = new MemberUtils();
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		if(year==null) {
			year="0";
			
		}
		model.addAttribute("year", year);
		
		
		List<Payment> payments = paymentRepository.findAll();


		
		List<String> yearList = new ArrayList<String>();
		
		Double totalPayments = 0.0;
		
		for (Payment payment : payments) {
			String yearStr = yearFormat.format(payment.getPaymentDate());
			if(!yearList.contains(yearStr)&&payment.getAmount()>0)yearList.add(yearStr);
			if(yearStr.equals(year)||"0".equals(year)) {
				totalPayments= totalPayments + payment.getAmount();
			}
			
		}
		
		yearList.sort(new StringComparator());
		
		model.addAttribute("yearList",yearList);
		
		String tpStr = totalPayments.toString();
		
		model.addAttribute("totalPayments", tpStr);
				
		return "monthlyRevenue";
		
	}
	
	@GetMapping("/monthlySubscriptions")
	public String getMonthlySubscriptions(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model, HttpServletRequest httpRequest) throws AuthenticationException {
		
		MemberUtils memberUtils = new MemberUtils();
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		return "monthlySubscriptions";
		
	}
	
	@GetMapping("/navbar")
	public String getNavbar(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model, HttpServletRequest httpRequest) {
		
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return "noaccess";
		
		MemberUtils memberUtils = new MemberUtils();
		
		memberUtils.checkCookieMember(loggedMember, memberRepository, model);
				
		return "navbar";
		
	}
	
	
	
	

}

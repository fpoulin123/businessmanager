package ca.xpertproject.apps.businessmanager.controller;

import java.net.URI;
import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class GeneralController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
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
	public String getMonthlyRevenue(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) throws AuthenticationException {
		
		MemberUtils memberUtils = new MemberUtils();
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		return "monthlyRevenue";
		
	}
	
	@GetMapping("/monthlySubscriptions")
	public String getMonthlySubscriptions(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) throws AuthenticationException {
		
		MemberUtils memberUtils = new MemberUtils();
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		return "monthlySubscriptions";
		
	}
	
	@GetMapping("/navbar")
	public String getNavbar(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) {
		
		MemberUtils memberUtils = new MemberUtils();
		
		memberUtils.checkCookieMember(loggedMember, memberRepository, model);
				
		return "navbar";
		
	}
	
	
	
	

}

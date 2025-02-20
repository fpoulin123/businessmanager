package ca.xpertproject.apps.businessmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;



@Controller
public class GeneralController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/home")
	public String getEvent(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) {
		
		System.out.println("Logged member : " + loggedMember);
		
		MemberUtils memberUtils = new MemberUtils();
		
		memberUtils.checkCookieMember(loggedMember, memberRepository, model);
		
		model.asMap().entrySet().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().toString()));
				
		return "home";
		
	}
	
	@GetMapping("/navbar")
	public String getNavbar(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) {
		
		System.out.println("Logged member : " + loggedMember);
		
		MemberUtils memberUtils = new MemberUtils();
		
		memberUtils.checkCookieMember(loggedMember, memberRepository, model);
		
		return "navbar";
		
	}
	
	
	

}

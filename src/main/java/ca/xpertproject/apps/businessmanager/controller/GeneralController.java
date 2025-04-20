package ca.xpertproject.apps.businessmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
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
	public String getEvent(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) throws AuthenticationException {
		
		MemberUtils memberUtils = new MemberUtils();
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model)) {
			System.out.println("Check cookie failed");
			
			return "redirect:signin";
		};
				
		return "home";
		
	}
	
	@GetMapping("/navbar")
	public String getNavbar(@CookieValue(value = "mybusinessLoggedMember", defaultValue = "guest") String loggedMember, Model model) {
		
		MemberUtils memberUtils = new MemberUtils();
		
		memberUtils.checkCookieMember(loggedMember, memberRepository, model);
				
		return "navbar";
		
	}
	
	
	
	

}

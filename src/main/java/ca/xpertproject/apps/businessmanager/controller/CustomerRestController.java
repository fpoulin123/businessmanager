package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.StringComparator;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;



@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
		
	MemberUtils memberUtils = new MemberUtils();
	
	
	@GetMapping("getCustomerList")
	public List<String> getCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember){
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.EMPTY_LIST;
		
		List<Customer> customerList = customerRepository.findAll();
		
		System.out.println("SIZE: " + customerList.size());
		
		List<String> customerForAutoComplete = (List<String>) customerList.stream().map(customer-> convertForAC(customer)).collect(Collectors.toList());
		
		customerForAutoComplete.sort(new StringComparator());
		
		return customerForAutoComplete;
	}

	private String convertForAC(Customer customer) {

		
		String customerDisplay = customer.getFirstName() + " " + customer.getLastName() + " " + customer.getId();
		
		return customerDisplay.trim();
	}

}

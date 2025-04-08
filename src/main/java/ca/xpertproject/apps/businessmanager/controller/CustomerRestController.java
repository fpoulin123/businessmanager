package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

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
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;
import ca.xpertproject.apps.businessmanager.objects.mappers.CustomerMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;



@RestController
public class CustomerRestController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MemberRepository memberRepository;
		
	MemberUtils memberUtils = new MemberUtils();
	
	
	@GetMapping("getCustomerList")
	public List<CustomerLight> getCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember){
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.emptyList();

		
		List<CustomerLight> customerList = (List<CustomerLight>) customerRepository.findAll().stream().map(customer-> CustomerMapper.convertForList(customer)).collect(Collectors.toList());
		
		
		return customerList;
	}

	
}

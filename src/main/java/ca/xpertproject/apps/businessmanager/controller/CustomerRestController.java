package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.EventAttendee;
import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;
import ca.xpertproject.apps.businessmanager.objects.mappers.CustomerMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;



@RestController
public class CustomerRestController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	EventAttendeeRepository attendeeRepository;

	@Autowired
	MemberRepository memberRepository;

	MemberUtils memberUtils = new MemberUtils();


	@GetMapping("getCustomerList")
	public List<CustomerLight> getCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, HttpServletRequest httpRequest){

		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return null;

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.emptyList();


		List<CustomerLight> customerList = (List<CustomerLight>) customerRepository.findAll().stream().map(customer-> CustomerMapper.convertForList(customer)).collect(Collectors.toList());


		return customerList;
	}


	@GetMapping("getACEventCustomerList")
	public List<CustomerLight> getACEventCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required=false) Long eventId, HttpServletRequest httpRequest){

		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return null;

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.emptyList();

		System.out.println("Event id : " + eventId);

		if(eventId!=null) {
			System.out.println("Filter list for event");
			List<Long> attendeeCustIdList = attendeeRepository.findByEventId(eventId).stream().map(EventAttendee::getCustomerId).collect(Collectors.toList());

			return customerRepository.findAll().stream().filter(customer -> !attendeeCustIdList.contains(customer.getId())).map(c -> CustomerMapper.convertForList(c)).collect(Collectors.toList());

		}
		System.out.println("No filter on list");
		return customerRepository.findAll().stream().map(customer-> CustomerMapper.convertForList(customer)).collect(Collectors.toList());
	}



}

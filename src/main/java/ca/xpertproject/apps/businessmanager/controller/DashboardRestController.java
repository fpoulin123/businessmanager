package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.dashboard.IWidgetsDataFeeder;
import ca.xpertproject.apps.businessmanager.dashboard.WidgetsDataFeeder;
import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.MonthlyCA;
import ca.xpertproject.apps.businessmanager.objects.MonthlySubNbr;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DashboardRestController {
		
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private EventAttendeeRepository eventAttendeeRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	IWidgetsDataFeeder dataFeeder = new WidgetsDataFeeder();
	
	@GetMapping("paymentsData")
	public List<Double> getPaymentsData(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,Model model, HttpServletRequest httpRequest){
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return null;
		return dataFeeder.getPaymentsData(paymentRepository, subscriptionRepository);
	}
	
	@GetMapping("validSubscriptionNumber")
	public Integer getValidSubscriptionNumber(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,Model model, HttpServletRequest httpRequest) {
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return null;
		return dataFeeder.getValidSubscriptionNumber(subscriptionRepository);
	}
	
	@GetMapping("unpaidSubs")
	public Integer getPartialOrUnpaidSubscriptions(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,Model model, HttpServletRequest httpRequest) {
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return null;
		return dataFeeder.getPartialOrUnpaidSubscriptions(subscriptionRepository);
	}
	
	@GetMapping("monthlyCA")
	public List<MonthlyCA> getCAByMonth(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false) String year,Model model, HttpServletRequest httpRequest) throws ParseException{
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return null;
		return dataFeeder.getCAByMonth(paymentRepository, subscriptionRepository, eventAttendeeRepository, year);
	}
	
	@GetMapping("monthlySubs")
	public List<MonthlySubNbr> getSubsByMonth(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,Model model, HttpServletRequest httpRequest){
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return null;
		return dataFeeder.getSubsByMonth(subscriptionRepository);
	}

}

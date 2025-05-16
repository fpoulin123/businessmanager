package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.StringComparator;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.objects.PaymentExt;
import ca.xpertproject.apps.businessmanager.objects.SubscriptionExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.SubscriptionMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.PageUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

	SubscriptionMapper mapper = new SubscriptionMapper();

	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	@GetMapping("/allSubscriptions")
	public String getAllSubscriptions(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false, defaultValue = "1") String page,
			@RequestParam(required = false, defaultValue = "") String year, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<String> yearList = new ArrayList<String>();

		yearList.add("");

		for (Subscription subscription : subscriptions) {
			String yearStr = yearFormat.format(subscription.getSubscriptionDate());
			if (!yearList.contains(yearStr))
				yearList.add(yearStr);
		}

		yearList.sort(new StringComparator());

		model.addAttribute("yearList", yearList);

		model.addAttribute("year", year);

		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub -> mapper.convert(sub))
				.filter(subExt -> matchYear(subExt, year)).collect(Collectors.toList());

		PageUtils.getPagedItems(subscriptionExtList, model, page, "subscriptions");

		return "subscriptions";
	}

	private boolean matchYear(SubscriptionExt subscription, String year) {
		if (year == null || "".equals(year))
			return true;

		return year.equals(yearFormat.format(subscription.getSubscriptionDate()));
	}

	@GetMapping("/validSubscriptions")
	public String getValidSubscriptions(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model,
			HttpServletRequest httpRequest) throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub -> mapper.convert(sub))
				.filter(t -> t.getIsValid()).collect(Collectors.toList());

		model.addAttribute("subscriptions", subscriptionExtList);

		return "subscriptions";
	}

	@GetMapping("/expiredSubscriptions")
	public String getExpiredSubscriptions(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model,
			HttpServletRequest httpRequest) throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub -> mapper.convert(sub))
				.filter(t -> !t.getIsValid()).collect(Collectors.toList());

		model.addAttribute("subscriptions", subscriptionExtList);

		return "subscriptions";
	}

	@GetMapping("/subscriptionsCloseToEnd")
	public String getSubscriptionsCloseToEnd(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model,
			HttpServletRequest httpRequest) {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<SubscriptionExt> subscriptionExtList = subscriptionRepository.findAll().stream()
				.map(sub -> mapper.convert(sub)).filter(t -> willExpire(t)).collect(Collectors.toList());

		model.addAttribute("subscriptions", subscriptionExtList);

		return "subscriptions";
	}

	@GetMapping("/subscriptionsExpiredThirty")
	public String getSubscriptionsExpiredThirty(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model,
			HttpServletRequest httpRequest) {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<SubscriptionExt> subscriptionExtList = subscriptionRepository.findAll().stream()
				.map(sub -> mapper.convert(sub)).filter(t -> hasExpired(t)).collect(Collectors.toList());

		model.addAttribute("subscriptions", subscriptionExtList);

		return "subscriptions";
	}

	private boolean willExpire(SubscriptionExt t) {

		LocalDate lcNow = LocalDate.now();

		LocalDate preavisLc = lcNow.plusDays(15);

		LocalDate expireDateLocal = t.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return preavisLc.isAfter(expireDateLocal) && LocalDate.now().isBefore(expireDateLocal);
	}

	private boolean hasExpired(SubscriptionExt t) {

		LocalDate lcNow = LocalDate.now();

		LocalDate preavisLc = lcNow.minusDays(30);

		LocalDate expireDateLocal = t.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return preavisLc.isBefore(expireDateLocal) && LocalDate.now().isAfter(expireDateLocal);
	}

	@GetMapping("/subscriptions")
	public String getSubscriptions(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = true) Long customerId, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<Subscription> subscriptions = subscriptionRepository.findAll();

		List<SubscriptionExt> subscriptionExtList = subscriptions.stream().map(sub -> mapper.convert(sub))
				.filter(t -> customerId.equals(t.getCustomerId())).collect(Collectors.toList());

		model.addAttribute("subscriptions", subscriptionExtList);

		model.addAttribute("customerId", customerId);

		return "subscriptions";
	}

	@GetMapping("/subscription")
	public String getSubscription(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = true) Long id, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		Subscription subscription = subscriptionRepository.findById(id);

		model.addAttribute("subscription", subscription);

		long subscriptionDateTime = subscription.getSubscriptionDate().getTime();

		int duration = subscription.getDuration();

		long durationLong = Integer.toUnsignedLong(duration);

		long dayInMillis = 24 * 3600 * 1000;

		long durationDateTime = durationLong * dayInMillis;

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
	public String getAddSubscription(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false) Long customerId, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		if (customerId != null) {
			Customer customer = customerRepository.findById(customerId);
			String fullName = customer.getFirstName() + " " + customer.getLastName();
			fullName = fullName.trim();
			model.addAttribute("customerFullName", fullName);
			model.addAttribute("customerLocked", true);
		} else {
			model.addAttribute("customerLocked", false);
		}

		model.addAttribute("customerId", customerId);

		return "createSubscriptionform";
	}

	@PostMapping("/addSubscription")
	public String addSubscription(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam Map<String, String> body, HttpServletResponse response, Model model,
			HttpServletRequest httpRequest) throws ParseException, AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		boolean taekwondo = "on".equals(body.get("taekwondo")) ? true : false;
		boolean kickboxing = "on".equals(body.get("kickboxing")) ? true : false;
		boolean taekibodo = "on".equals(body.get("taekibodo")) ? true : false;

		String subscriptionDateValue = body.get("subscriptionDate");
		Date subscriptionDate = null;

		if (subscriptionDateValue != null && !(subscriptionDateValue.isEmpty())) {
			subscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("subscriptionDate"));
		}

		Customer customer = customerRepository.findById(Long.parseLong(body.get("customerid")));

		Subscription subscription = GenericBuilder.of(Subscription::new).with(Subscription::setCustomer, customer)
				.with(Subscription::setDuration, Integer.parseInt(body.get("duration")))
				.with(Subscription::setTaekwondo, taekwondo).with(Subscription::setKickboxing, kickboxing)
				.with(Subscription::setTaekibodo, taekibodo)
				.with(Subscription::setAmount, Double.parseDouble(body.get("amount")))
				.with(Subscription::setSubscriptionDate, subscriptionDate).build();

		subscription = subscriptionRepository.save(subscription);

		return "redirect:./subscription?id=" + subscription.id;
	}

	@GetMapping("/modifySubscription")
	public String modifySubscriptiont(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = true) Long id, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		Subscription subscription = subscriptionRepository.findById(id);

		model.addAttribute("subscription", subscription);

		return "modifySubscriptionForm";
	}

	@PostMapping("/modifySubscription")
	public String modifySubscription(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam Map<String, String> body, HttpServletResponse response, Model model,
			HttpServletRequest httpRequest) throws ParseException, AuthenticationException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)
				|| !memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		boolean taekwondo = "on".equals(body.get("taekwondo")) ? true : false;
		boolean kickboxing = "on".equals(body.get("kickboxing")) ? true : false;
		boolean taekibodo = "on".equals(body.get("taekibodo")) ? true : false;

		String subscriptionDateValue = body.get("subscriptionDate");
		Date subscriptionDate = null;

		if (subscriptionDateValue != null && !(subscriptionDateValue.isEmpty())) {
			subscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("subscriptionDate"));
		}

		Subscription subscription = subscriptionRepository.findById(Long.parseLong(body.get("id")));

		Customer customer = customerRepository.findById(Long.parseLong(body.get("customerid")));

		subscription = GenericBuilder.of(Subscription::new).with(Subscription::setId, subscription.getId())
				.with(Subscription::setCustomer, customer)
				.with(Subscription::setDuration, Integer.parseInt(body.get("duration")))
				.with(Subscription::setTaekwondo, taekwondo).with(Subscription::setKickboxing, kickboxing)
				.with(Subscription::setTaekibodo, taekibodo)
				.with(Subscription::setAmount, Double.parseDouble(body.get("amount")))
				.with(Subscription::setSubscriptionDate, subscriptionDate).build();

		subscription = subscriptionRepository.save(subscription);

		return "redirect:./subscription?id=" + subscription.id;
	}

	

}

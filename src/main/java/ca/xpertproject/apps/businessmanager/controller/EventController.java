package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.StringComparator;
import ca.xpertproject.apps.businessmanager.objects.PaymentExt;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

	@GetMapping("/allEvents")
	public String getAllEvents(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, @RequestParam(required = false, defaultValue = "") String year, Model model) {
		

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		
		List<Event> eventList = eventRepository.findAll().stream().filter(event -> matchYear(event, year)).collect(Collectors.toList());
		
		List<String> yearList = new ArrayList<String>();
		
		yearList.add("");
		
		yearList.sort(new StringComparator());
		
		for (Event event : eventList) {
			String yearStr = yearFormat.format(event.getEventDate());
			if(!yearList.contains(yearStr))yearList.add(yearStr);
		}
		
		model.addAttribute("yearList",yearList);
		
		model.addAttribute("year", year);
		
		
		model.addAttribute("eventList", eventList);
		
		return "events";
	}
	
	private boolean matchYear(Event event, String year) {
		if(year==null||"".equals(year))return true;
		
		return year.equals(yearFormat.format(event.getEventDate()));
	}
	
	
}

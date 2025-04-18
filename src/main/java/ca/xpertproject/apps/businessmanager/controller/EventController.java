package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.model.EventAttendee;
import ca.xpertproject.apps.businessmanager.model.EventAttendeeRepository;
import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.StringComparator;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EventAttendeeRepository eventAttendeeRepository;
	
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
	
	@GetMapping("/event")
	public String viewEvent(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = true) Long id, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Event event = eventRepository.findById(id);
		
		model.addAttribute("event", event);
		
		List<EventAttendee> attendees = eventAttendeeRepository.findByEventId(id);
		
		model.addAttribute("attendees", attendees);
		
		model.addAttribute("nbrAttendees", attendees.size());
		
		return "event";
	}
	
	@GetMapping("/calendar")
	public String viewCalendar(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		return "calendar";
	}
	
	@GetMapping("/calendar_example")
	public String viewCalendarExpl(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model) {
		
		return "calendar_example ";
	}
	
	@GetMapping("/createEvent")
	public String createEvent(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model) {


		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		
		return "createEventForm";
	}
	
	@PostMapping("/createEvent")
	public String createEvent(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) throws ParseException {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		Event event = new Event();
		
		String eventDateStr = body.get("eventDate");
		
		Date eventDate = null;
		
		if(eventDateStr!=null &&  !(eventDateStr.isEmpty())) {
			eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(eventDateStr);
		}
		
		String endDateStr = body.get("endDate");
		
		Date endDate = null;
		
		if(endDateStr!=null &&  !(endDateStr.isEmpty())) {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
		}
		
		String amountStr= body.get("amount");
		
		event.setEventName(body.get("eventName"));
		event.setEventType(body.get("eventType"));
		event.setEventDate(eventDate);
		event.setEndDate(endDate);
		event.setAmount(Double.parseDouble(amountStr));
		
		event = eventRepository.save(event);
		
		return "redirect:/event?id=" + event.id;
	}
	
	@GetMapping("/editEvent")
	public String editEvent(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = true) Long id,Model model) {

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Event event = eventRepository.findById(id);
		
		model.addAttribute("event", event);
		
		return "editEventForm";
	}
	
	@PostMapping("/updateEvent")
	public String updateEvent(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) throws ParseException {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		Event event = eventRepository.findById(Long.parseLong(body.get("id")));
		
		String eventDateStr = body.get("eventDate");
		
		Date eventDate = null;
		
		if(eventDateStr!=null &&  !(eventDateStr.isEmpty())) {
			eventDate = new SimpleDateFormat("yyyy-MM-dd").parse(eventDateStr);
		}
		
		String endDateStr = body.get("endDate");
		
		Date endDate = null;
		
		if(endDateStr!=null &&  !(endDateStr.isEmpty())) {
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
		}
		
		String amountStr= body.get("amount");
		
		event.setEventName(body.get("eventName"));
		event.setEventType(body.get("eventType"));
		event.setEventDate(eventDate);
		event.setEndDate(endDate);
		event.setAmount(Double.parseDouble(amountStr));
		
		event = eventRepository.save(event);
		
		return "redirect:/event?id=" + event.id;
	}
	
	@GetMapping("/addAttendee")
	public String addAttendee(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false) Long eventId,Model model) {

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		if(eventId!=null) {
			Event event = eventRepository.findById(eventId);
			model.addAttribute("event", event);
			model.addAttribute("eventLocked", true);
		}else {
			model.addAttribute("eventLocked", false);
		}
				
		return "addAttendeeForm";
	}
	
	@PostMapping("/addAttendee")
	public String addAttendee(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) {

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		body.entrySet().forEach(attr -> System.out.println(attr.getKey() + " : " + attr.getValue()));
		
		EventAttendee attendee = new EventAttendee();
		
		attendee.setEventId(Long.parseLong(body.get("eventId")));
		
		Customer customer = customerRepository.findById(Long.parseLong(body.get("customerId")));
		
		attendee.setCustomer(customer);
		
		attendee.setAmount(Double.parseDouble(body.get("amount")));
		
		attendee.setPayed(("on".equals(body.get("payed")))?true:false);
		
		attendee = eventAttendeeRepository.save(attendee);
				
		return "redirect:/event?id=" +attendee.getEventId();
	}
	
	@GetMapping("/editAttendee")
	public String editAttendee(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false) Long id,Model model) {

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		EventAttendee attendee = eventAttendeeRepository.findById(id);
		Event event = eventRepository.findById(attendee.getEventId());
		model.addAttribute("attendee", attendee);
		model.addAttribute("event", event);
		return "editAttendeeForm";
	}
	
	@PostMapping("/updateAttendee")
	public String updateAttendee(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model) {

		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		body.entrySet().forEach(attr -> System.out.println(attr.getKey() + " : " + attr.getValue()));
		
		EventAttendee attendee = eventAttendeeRepository.findById(Long.parseLong(body.get("attendeeId")));
		
		attendee.setAmount(Double.parseDouble(body.get("amount")));
		
		attendee.setPayed(("on".equals(body.get("payed")))?true:false);
		
		attendee = eventAttendeeRepository.save(attendee);
				
		return "redirect:/event?id=" +attendee.getEventId();
	}
	
	@GetMapping("/deleteAttendee")
	public String deleteAttendee(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false) Long id,Model model) {
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
				
		EventAttendee attendee = eventAttendeeRepository.findById(id);
		
		Long eventId = attendee.getEventId();
		
		eventAttendeeRepository.deleteById(id);
		
		return "redirect:/event?id=" + eventId;
	}
	
}

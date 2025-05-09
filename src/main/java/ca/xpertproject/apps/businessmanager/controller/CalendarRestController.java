package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.Event;
import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.objects.CalendarData;
import ca.xpertproject.apps.businessmanager.objects.CalendarEvent;
import ca.xpertproject.apps.businessmanager.objects.CustomerLight;
import ca.xpertproject.apps.businessmanager.objects.mappers.CustomerMapper;
import ca.xpertproject.apps.businessmanager.objects.mappers.EventMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CalendarRestController {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	EventMapper mapper = new EventMapper();
	
	@GetMapping("calendarElts")
	public CalendarData getCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, HttpServletRequest httpRequest){
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return "noaccess";
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository)) return null;

		List<CalendarEvent> calendarEventList = eventRepository.findAll().stream().map(event -> mapper.convert(event)).collect(Collectors.toList());
		
		CalendarData calendarData = new CalendarData();
		calendarData.setCurrentDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		calendarData.setEventList(calendarEventList);
		return calendarData;
	}

}

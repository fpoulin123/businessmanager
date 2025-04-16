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

import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.objects.EventForAC;
import ca.xpertproject.apps.businessmanager.objects.mappers.EventMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;

@RestController
public class EventRestController {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	MemberRepository memberRepository;
		
	MemberUtils memberUtils = new MemberUtils();
	
	
	@GetMapping("/getACEventList")
	public List<EventForAC> getACEventList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember){
		
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.emptyList();

		
		List<EventForAC> eventList = (List<EventForAC>) eventRepository.findAll().stream().map(event-> EventMapper.convertForList(event)).collect(Collectors.toList());
		
		
		return eventList;
	}

	
}

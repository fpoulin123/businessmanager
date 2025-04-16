package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.EventRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;

@WebMvcTest(EventRestController.class)
public class EventRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private EventRepository eventRepository;
	
	@MockitoBean
	private MemberRepository memberRepository;
	
	@Mock
	private MemberUtils memberUtils;
	
	Cookie loginCookie = null;
	
	EasyRandom easyRandom = new EasyRandom();
	
	@BeforeEach
	public void init() {
		loginCookie=new Cookie(MEMBER_LOGGED_COOKIE_NAME, "admin@mybusiness.ca");
        loginCookie.setMaxAge(3600*24);
        
	}

	@Test
	public void getACEventList() throws Exception {
		mockMvc.perform(get("/getACEventList")).andExpect(status().isOk());
	}

}

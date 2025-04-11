package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;

@WebMvcTest(DownloadController.class)
public class DownloadControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private SubscriptionRepository subscriptionRepository;
	
	@MockitoBean
	private CustomerRepository customerRepository;
	
	@MockitoBean
	private MemberRepository memberRepository;
	
	@MockitoBean
	private PaymentRepository paymentRepository;
	
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
	public void downloadCustomers_notLogged() throws Exception {
		
		mockMvc.perform(get("/downloadCustomers")).andExpect(status().isOk());
	}
	
	@Test
	public void downloadSubscriptions_notLogged() throws Exception {
		
		mockMvc.perform(get("/downloadSubscriptions")).andExpect(status().isOk());
	}
	
	@Test
	public void downloadPayments_notLogged() throws Exception {
		
		mockMvc.perform(get("/downloadPayments")).andExpect(status().isOk());
	}
	
	@Test
	public void downloadCustomers_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
				
		mockMvc.perform(get("/downloadCustomers").cookie(loginCookie)).andExpect(status().isOk());
	}
	
	@Test
	public void downloadSubscriptions_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
				
		mockMvc.perform(get("/downloadSubscriptions").cookie(loginCookie)).andExpect(status().isOk());
	}
	
	@Test
	public void downloadPayments_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
				
		mockMvc.perform(get("/downloadPayments").cookie(loginCookie)).andExpect(status().isOk());
	}

}

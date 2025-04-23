package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.COOKIE_ENCRYPTION_KEY;
import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.EncryptUtils;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {
	
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
	public void init() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		loginCookie=new Cookie(MEMBER_LOGGED_COOKIE_NAME, EncryptUtils.encryptAES(COOKIE_ENCRYPTION_KEY, "admin@mybusiness.ca"));
        loginCookie.setMaxAge(3600*24);
        
	}
	
	@Test 
	public void allSubscription_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/allSubscriptions")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void validSubscription_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/validSubscriptions")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void subscriptions_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/subscriptions").param("customerId", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void subscription_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/subscription").param("id", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void addSubscription_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/addSubscription")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void modifySubscription_notLogged_noAccess() throws Exception {
		mockMvc.perform(get("/modifySubscription").param("id", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test 
	public void allSubscription_Logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		//when(customerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		
		mockMvc.perform(get("/allSubscriptions").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("subscriptions"));
		
	}
	
	@Test 
	public void validSubscription_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		mockMvc.perform(get("/validSubscriptions").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("subscriptions"));
		
	}
	
	@Test 
	public void subscriptions_logged() throws Exception {
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		mockMvc.perform(get("/subscriptions").cookie(loginCookie).param("customerId", "1")).andExpect(status().isOk()).andExpect(view().name("subscriptions"));
		
	}
	
	@Test 
	public void subscription_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		when(subscriptionRepository.findById(anyLong())).thenReturn(subscription);
		
		mockMvc.perform(get("/subscription").cookie(loginCookie).param("id", "1")).andExpect(status().isOk()).andExpect(view().name("subscription"));
		
	}
	
	@Test 
	public void addSubscription_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		mockMvc.perform(get("/addSubscription").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("createSubscriptionform")).andExpect(model().attribute("customerLocked", equalTo(false)));
		
	}
	
	@Test 
	public void addSubscription_logged_withCustomerId() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Customer customer = easyRandom.nextObject(Customer.class);
		
		when(customerRepository.findById(any())).thenReturn(customer);
		
		mockMvc.perform(get("/addSubscription").cookie(loginCookie).param("customerId", "1")).andExpect(status().isOk()).andExpect(view().name("createSubscriptionform")).andExpect(model().attribute("customerLocked", equalTo(true)));
		
	}
	
	@Test 
	public void modifySubscription_logged_typeTrue() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		subscription.setTaekibodo(true);
		subscription.setTaekwondo(true);
		subscription.setKickboxing(true);
		
		
		when(subscriptionRepository.findById(anyLong())).thenReturn(subscription);
		
		mockMvc.perform(get("/modifySubscription").cookie(loginCookie)
				.param("id", "1")
				.param("customerid", "1")
				.param("duration", "30")
				.param("amount", "850")
				.param("subscriptionDate", "2025-01-01")
				.param("taekibodo", "on")
				.param("taekwondo", "on")
				.param("kickboxing", "on")
			).andExpect(status().isOk()).andExpect(view().name("modifySubscriptionForm")).andExpect(model().attribute("subscription", equalTo(subscription)));
		
	}
	
	@Test 
	public void modifySubscription_logged_typeFalse() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		subscription.setTaekibodo(false);
		subscription.setTaekwondo(false);
		subscription.setKickboxing(false);
		
		when(subscriptionRepository.findById(anyLong())).thenReturn(subscription);
		
		mockMvc.perform(get("/modifySubscription")
				.cookie(loginCookie)
				.param("id", "1")
				.param("customerid", "1")
				.param("duration", "30")
				.param("amount", "850")
				.param("subscriptionDate", "2025-01-01")
				.param("taekibodo", "")
				.param("taekwondo", "")
				.param("kickboxing", "")
			).andExpect(status().isOk()).andExpect(view().name("modifySubscriptionForm")).andExpect(model().attribute("subscription", equalTo(subscription)));
		
	}
	
	@Test
	public void addSubscriptionPost_notLogged_noAccess() throws Exception {
		
		mockMvc.perform(post("/addSubscription")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}

	@Test
	public void addSubscriptionPost_logged() throws Exception {

		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		when(subscriptionRepository.save(any())).thenReturn(subscription);
		
		mockMvc.perform(post("/addSubscription").cookie(loginCookie)
				.param("customerid", "1")
				.param("duration", "30")
				.param("amount", "850")
				.param("subscriptionDate", "2025-01-01")
				.param("taekibodo", "on")
				.param("taekwondo", "on")
				.param("kickboxing", "on")
				).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:./subscription?id=" + subscription.id));
	}
	
	@Test
	public void modifySubscriptionPost_logged() throws Exception {

		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Subscription subscription = easyRandom.nextObject(Subscription.class);
		
		when(subscriptionRepository.findById(any())).thenReturn(subscription);
		
		when(subscriptionRepository.save(any())).thenReturn(subscription);
		
		mockMvc.perform(post("/modifySubscription").cookie(loginCookie)
				.param("id", "1")
				.param("customerid", "1")
				.param("duration", "30")
				.param("amount", "850")
				.param("subscriptionDate", "2025-01-01")
				).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:./subscription?id=" + subscription.id));
	}


}

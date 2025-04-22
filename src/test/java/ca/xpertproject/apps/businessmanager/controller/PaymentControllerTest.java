package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.COOKIE_ENCRYPTION_KEY;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.EncryptUtils;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {
	
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
	public void allPayments_notLogged() throws Exception {
		mockMvc.perform(get("/allPayments")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test
	public void payments_notLogged() throws Exception {
		mockMvc.perform(get("/payments").param("subscriptionId", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	@Test
	public void addPayment_notLogged() throws Exception {
		mockMvc.perform(get("/addPayment").param("subscriptionId", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
		
	}
	
	/*-------------------------------------------*/
	
	@Test
	public void allPayments_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		mockMvc.perform(get("/allPayments").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("payments"));
		
	}
	
	@Test
	public void payments_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		mockMvc.perform(get("/payments").cookie(loginCookie).param("subscriptionId", "1")).andExpect(status().isOk()).andExpect(view().name("payments"));
		
	}
	
	@Test
	public void addPayment_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		mockMvc.perform(get("/addPayment").cookie(loginCookie).param("subscriptionId", "1")).andExpect(status().isOk()).andExpect(view().name("createPaymentForm"));
		
	}
	
	@Test
	public void addPaymentPost_notLogged_noAccess() throws Exception {
		
		mockMvc.perform(post("/addPayment")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void addPaymentPost_logged() throws Exception {

		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		Payment payment = easyRandom.nextObject(Payment.class);
		
		when(paymentRepository.save(any())).thenReturn(payment);
		
		mockMvc.perform(post("/addPayment").cookie(loginCookie)
				.param("subscriptionId", "1")
				.param("amount", "850")
				.param("paymentDate", "2025-01-01")
				).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:./subscription?id=1"));
	}
}

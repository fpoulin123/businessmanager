package ca.xpertproject.apps.businessmanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.PaymentRepository;
import ca.xpertproject.apps.businessmanager.model.SubscriptionRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;

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

}

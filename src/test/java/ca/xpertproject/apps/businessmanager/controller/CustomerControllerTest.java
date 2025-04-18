package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockitoBean
	private CustomerRepository customerRepository;
	
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
	public void allCustomers_notLogged_noaccess() throws Exception {
		mockMvc.perform(get("/allCustomers")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void customers_notLogged_noaccess() throws Exception {
		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void customer_notLogged_noaccess() throws Exception {
		mockMvc.perform(get("/customer").param("id", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void createCustomer_notLogged_noaccess() throws Exception {
		mockMvc.perform(get("/createCustomer")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void editCustomer_notLogged_noaccess() throws Exception {
		mockMvc.perform(get("/editCustomer").param("id", "1")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void allCustomers_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		//when(memberUtils.checkCookieMember(any(), any(), any())).thenReturn(true);
		
		when(customerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		
		mockMvc.perform(get("/allCustomers").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("customers"));
	}
	
	@Test
	public void customers_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		//when(memberUtils.checkCookieMember(any(), any(), any())).thenReturn(true);
		
		when(customerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		
		mockMvc.perform(get("/customers").cookie(loginCookie).param("name", "name")).andExpect(status().isOk()).andExpect(view().name("customers"));
		
		mockMvc.perform(get("/customers").cookie(loginCookie).param("firstname", "firstname")).andExpect(status().isOk()).andExpect(view().name("customers"));
	}
	
	@Test
	public void customer_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		Customer customer = easyRandom.nextObject(Customer.class);
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		when(customerRepository.findById(anyLong())).thenReturn(customer);
		
		mockMvc.perform(get("/customer").cookie(loginCookie).param("id", "1")).andExpect(status().isOk()).andExpect(view().name("customer"));
	}
	
	@Test
	public void editCustomer_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		Customer customer = easyRandom.nextObject(Customer.class);
				
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		when(customerRepository.findById(anyLong())).thenReturn(customer);
		
		mockMvc.perform(get("/editCustomer").cookie(loginCookie).param("id", "1")).andExpect(status().isOk()).andExpect(view().name("editCustomerForm"));
	}
	
	@Test
	public void createCustomer_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		mockMvc.perform(get("/createCustomer").cookie(loginCookie)).andExpect(status().isOk()).andExpect(view().name("createCustomerform"));
	}
	
	@Test
	public void createCustomerPost_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		MockMultipartFile mockFile = new MockMultipartFile("picture", "picture.jpg", "text/plain", "my beautiful picture".getBytes());
		
		Customer customer = easyRandom.nextObject(Customer.class);
		
		customer.setBarcodeValue(null);
		
		when(customerRepository.save(any())).thenReturn(customer);
	
		mockMvc.perform(multipart("/createCustomer").file(mockFile).cookie(loginCookie).param("firstname", "firstname").param("lastname","lastname")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:./customer?id=" + customer.getId()));
	}
	
	@Test
	public void createCustomerPost_notLogged_noAccess() throws Exception {
		
		MockMultipartFile mockFile = new MockMultipartFile("picture", "picture.jpg", "text/plain", "my beautiful picture".getBytes());
		
		mockMvc.perform(multipart("/createCustomer").file(mockFile).param("firstname", "firstname").param("lastname","lastname")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void updateCustomerPost_notLogged_noAccess() throws Exception {

		MockMultipartFile mockFile = new MockMultipartFile("picture", "picture.jpg", "text/plain", "my beautiful picture".getBytes());
		
		mockMvc.perform(multipart("/updateCustomer").file(mockFile).param("firstname", "firstname").param("lastname","lastname")).andExpect(status().isOk()).andExpect(view().name("noaccess"));
	}
	
	@Test
	public void updateCustomerPost_logged() throws Exception {
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		MockMultipartFile mockFile = new MockMultipartFile("picture", "picture.jpg", "text/plain", "my beautiful picture".getBytes());
		
		Customer customer = easyRandom.nextObject(Customer.class);
		
		customer.setBarcodeValue(null);
		
		when(customerRepository.findById(any())).thenReturn(customer);
	
		mockMvc.perform(multipart("/updateCustomer").file(mockFile).cookie(loginCookie).param("id", "1").param("firstname", "firstname").param("lastname","lastname")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:./customer?id=" + customer.getId()));
	}
}

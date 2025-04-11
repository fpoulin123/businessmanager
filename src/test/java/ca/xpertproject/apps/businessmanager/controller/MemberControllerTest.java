package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockitoBean
	private MemberRepository memberRepository;
	
	@Mock
	private MemberUtils memberUtils;
	
	Cookie loginCookie = null;
	
	@BeforeEach
	public void init() {
		loginCookie=new Cookie(MEMBER_LOGGED_COOKIE_NAME, "admin@mybusiness.ca");
        loginCookie.setMaxAge(3600*24);
        
	}
	
	@Test
	public void signin_ok() throws Exception {
		
		mockMvc.perform(get("/signin")).andExpect(status().isOk()).andExpect(view().name("signin"));
		
	}
	
	@Test
	public void signup_ok() throws Exception {
		
		mockMvc.perform(get("/signup")).andExpect(status().isOk()).andExpect(view().name("signup"));
		
	}
	
	@Test
	public void authenticate_emailNull() throws Exception {
		
		mockMvc.perform(post("/signin")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "E-mail is empty."));
		
	}
	
	@Test
	public void authenticate_passwordNull() throws Exception {
		
		mockMvc.perform(post("/signin").param("email", "test@test.com")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Password is empty."));
		
	}
	
	@Test
	public void authenticate_ok() throws Exception {
		
		Member member = new Member();
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		member.setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		mockMvc.perform(post("/signin").param("email", "test@test.com").param("password", "password")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/home"));
		
	}
	
	@Test
	public void authenticate_membereInactive() throws Exception {
		
		Member member = new Member();
		member.setActive(false);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		member.setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		mockMvc.perform(post("/signin").param("email", "test@test.com").param("password", "password")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Inactive account. Please contact an administrator.")).andExpect(view().name("signin"));
		
	}
	
	@Test
	public void authenticate_noMemberFound() throws Exception {
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.emptyList());
		
		
		mockMvc.perform(post("/signin").param("email", "test@test.com").param("password", "password")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Wrong user name or password, please retry.")).andExpect(view().name("signin"));
		
	}
	
	@Test
	public void authenticate_wrongPassword() throws Exception {
		
		Member member = new Member();
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		member.setPassword("test");
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		mockMvc.perform(post("/signin").param("email", "test@test.com").param("password", "password")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Wrong user name or password, please retry.")).andExpect(view().name("signin"));
		
	}
	
	@Test
	public void createMember() throws Exception {
		
		mockMvc.perform(post("/signup")
				.param("email", "test@test.com")
				.param("password", "password")
				.param("firstname", "firstname")
				.param("lastname","lastname")
				).andExpect(status().isOk())
		.andExpect(view().name("signin"));
		
	}
	
	@Test
	public void getMember_ok() throws Exception {
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		member.setId(Long.valueOf(1));
		
		when(memberRepository.findByEmail(anyString())).thenReturn(Collections.singletonList(member));
		
		
		when(memberRepository.findById(any())).thenReturn(Optional.of(member));
		
		mockMvc.perform(get("/member").cookie(loginCookie).param("id", "1")).andExpect(status().isOk()).andExpect(model().attribute("member", member)).andExpect(view().name("member"));
		
	}
	
	@Test
	public void logOut() throws Exception {
				
		mockMvc.perform(get("/logout").cookie(loginCookie)).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/home"));
		
	}
	
}




package ca.xpertproject.apps.businessmanager.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import ca.xpertproject.apps.businessmanager.constant.ApplicationConstants;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;

@SpringBootTest
public class MemberUtilsTest {
	
	@Mock
	MemberRepository memberRepository;
	
	@Mock
	Model model;
	
	@Test
	public void checkCookieMember_notLogged() {
		MemberUtils memberUtils = new MemberUtils();
		
		
		boolean hasAccess = memberUtils.checkCookieMember("guest", memberRepository);
		
		assertFalse(hasAccess);
	}
	
	@Test
	public void checkCookieMember_validLogged() {
		MemberUtils memberUtils = new MemberUtils();
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		
		when(memberRepository.findByEmail(any())).thenReturn(Collections.singletonList(member));
		
		String email = null;
				
			try {
				email =	EncryptUtils.encryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, member.getEmail());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		boolean hasAccess = memberUtils.checkCookieMember(email , memberRepository);
				
		assertTrue(hasAccess);
	}
	
	@Test
	public void checkCookieMember_notLogged_modelHasMessage() {
		
		
		MemberUtils memberUtils = new MemberUtils();
		
		
		boolean hasAccess = memberUtils.checkCookieMember("guest", memberRepository, model);
		
		verify(model, times(1)).addAttribute("authErrorMsg", "Vous n'avez pas accès a cette resource. Merci de vous authentifier avec un compte ayant les droits adéquats. Merci.");
		
		assertFalse(hasAccess);
	}
	
	@Test
	public void checkCookieMember_loggedInactive_modelHasMessage() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
				
		MemberUtils memberUtils = new MemberUtils();
		
		Member member = new Member();
		
		member.setEmail("test@test.com");
		member.setActive(false);
		
		when(memberRepository.findByEmail(any())).thenReturn(Collections.singletonList(member));
		
		boolean hasAccess = memberUtils.checkCookieMember(EncryptUtils.encryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, member.getEmail()), memberRepository, model);
		
		verify(model, times(1)).addAttribute("authErrorMsg", "Your account seems to be inactive. Please ask to an administrator to solve the problem.");
		
		assertFalse(hasAccess);
	}
	
	
	@Test
	public void checkCookieMember_loggedValid_modelHasMessage() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
				
		MemberUtils memberUtils = new MemberUtils();
		
		Member member = new Member();
		
		member.setActive(true);
		member.setEmail("admin@mybusiness.ca");
		member.setFirstname("Admin");
		member.setLastname("MYBUSINESS");
		
		when(memberRepository.findByEmail(any())).thenReturn(Collections.singletonList(member));
		
		
		boolean hasAccess = memberUtils.checkCookieMember(EncryptUtils.encryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, member.getEmail()), memberRepository, model);
		
		verify(model, times(1)).addAttribute("memberName", "Admin MYBUSINESS");
		
		assertTrue(hasAccess);
	}
	
	
	@Test
	public void checkCookieMember_memberNotFound_modelHasMessage() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
				
		MemberUtils memberUtils = new MemberUtils();
		
		when(memberRepository.findByEmail(any())).thenReturn(Collections.emptyList());
		
		boolean hasAccess = memberUtils.checkCookieMember(EncryptUtils.encryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, "noone@thismail.com"), memberRepository, model);
		verify(model, times(1)).addAttribute("authErrorMsg", "Vous n'avez pas accès a cette resource. Merci de vous authentifier avec un compte ayant les droits adéquats. Merci.");
		
		assertFalse(hasAccess);
	}


}

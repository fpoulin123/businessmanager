package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.constant.ApplicationConstants;
import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.EncryptUtils;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MemberController{
	
	@Autowired
	MemberRepository memberRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	@ExceptionHandler(Exception.class)
	public String genericExceptionHandler(Exception exception, Model model) {
		String message = exception.getMessage();
		
		if(message.contains("ERROR: duplicate key value violates unique constraint \"unique_email\"")){
			
			model.addAttribute("errorMessage", "This e-mail has already been used, please sign in with it or use another one, Thank you!");
			return "signup";
		}
		
		if(exception.getClass().equals(AuthenticationException.class)) {
			model.addAttribute("errorMessage",message);
			return "signin";
		
		}
		
		exception.printStackTrace();

        return "error";
    }
	
	
	@GetMapping("/member")
	public String getMember(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,@RequestParam(required=false, defaultValue="1") Long id, Model model) {
		if(!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Optional<Member> memberOpt = memberRepository.findById(id);
		if(memberOpt.isPresent()) {
			Member member = memberOpt.get();
			model.addAttribute("member", member);
		}
		
		return "member";
	}
	
	
	
	@GetMapping("/signup")
	public String displayMemberCreationForm() {
		return ("signup");
	}
	
	@PostMapping("/signup")
	public String createMember(@RequestParam Map<String, String> body) {
		String firstname = body.get("firstname");
		String lastname = body.get("lastname");
		String email = body.get("email");
		String password = body.get("password");
		
		password = DigestUtils.sha256Hex(password);
		
		Member newMember = new Member();
		newMember.setFirstname(firstname);
		newMember.setLastname(lastname);
		newMember.setEmail(email);
		newMember.setPassword(password);
		newMember.setActive(false);
		memberRepository.save(newMember);
		
		return "signin";
	}
	
	@GetMapping("/signin")
	public String getLogin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String authenticate(@RequestParam Map<String, String> body, HttpServletResponse response) throws NoSuchAlgorithmException, AuthenticationException, InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		if(body.get("email")==null||body.get("email").isEmpty()) {
			throw new AuthenticationException("E-mail is empty.");
		}
		
		if(body.get("password")==null||body.get("password").isEmpty()) {
			throw new AuthenticationException("Password is empty.");
		}
		
		List<Member> potentialMembers = (List<Member>) memberRepository.findByEmail(body.get("email"));
		
		if(potentialMembers.isEmpty()) {
			throw new AuthenticationException("Wrong user name or password, please retry.");
		}
					
		Member member = Optional.of(potentialMembers.get(0)).orElse(null);
		
			
		String sentPassword = body.get("password");
		
		String sha256hex = DigestUtils.sha256Hex(sentPassword);
		
		if(sha256hex.equals(member.getPassword())) {
			
			if(!member.getActive()) {
				AuthenticationException excp = new AuthenticationException("Inactive account. Please contact an administrator.");
				
				throw excp;
			}
			
			Cookie loginCookie=new Cookie(MEMBER_LOGGED_COOKIE_NAME, EncryptUtils.encryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, member.getEmail()));
            loginCookie.setMaxAge(3600*24);
            response.addCookie(loginCookie);
			
            return "redirect:/home";
		}else {
			AuthenticationException excp = new AuthenticationException("Wrong user name or password, please retry.");
			
			throw excp;

		}
	
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		
		Cookie loginCookie=new Cookie(MEMBER_LOGGED_COOKIE_NAME, "");
        loginCookie.setMaxAge(0);
        response.addCookie(loginCookie);
		
		return "redirect:/home";
        
	}

}

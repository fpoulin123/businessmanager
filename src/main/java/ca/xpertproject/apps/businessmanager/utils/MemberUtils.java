package ca.xpertproject.apps.businessmanager.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.ui.Model;

import ca.xpertproject.apps.businessmanager.constant.ApplicationConstants;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;


public class MemberUtils {
	

	
	public boolean checkCookieMember(String loggedMember, MemberRepository memberRepository, Model model){
		
		boolean hasAccess=false;
		
		if(loggedMember!=null && !loggedMember.isEmpty() && !"guest".equals(loggedMember)) {
			
			String email = null;
			
			try {
				email = EncryptUtils.decryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, loggedMember);
			} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException
					| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<Member> memberOpt = memberRepository.findByEmail(email);
			
			if(!memberOpt.isEmpty()) {
				
				Member member = memberOpt.get(0);
				if(member.getActive()) {
					
					model.addAttribute("memberName", member.getFirstname() +  " " + member.getLastname());
					hasAccess = true;
				}else {
					
					model.addAttribute("authErrorMsg", "Your account seems to be inactive. Please ask to an administrator to solve the problem.");
				}
				
			}else {
				
				model.addAttribute("authErrorMsg", "Vous n'avez pas accès a cette resource. Merci de vous authentifier avec un compte ayant les droits adéquats. Merci.");			
			}
		}else {
			
			model.addAttribute("authErrorMsg", "Vous n'avez pas accès a cette resource. Merci de vous authentifier avec un compte ayant les droits adéquats. Merci.");			
		}
		
		return hasAccess;
	}
	
	public boolean checkCookieMember(String loggedMember, MemberRepository memberRepository) {
		
		boolean hasAccess=false;
		
		if(loggedMember!=null && !loggedMember.isEmpty() && !"guest".equals(loggedMember)) {
			String email = null;
			
			try {
				email = EncryptUtils.decryptAES(ApplicationConstants.COOKIE_ENCRYPTION_KEY, loggedMember);
			} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException
					| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("EMAIL : " + email);
			
			List<Member> memberOpt = memberRepository.findByEmail(email);
			
			if(!memberOpt.isEmpty()) {
				
				Member member = memberOpt.get(0);
				if(member.getActive())
				hasAccess = true;
			}
		}
		
		return hasAccess;
	}

}

package ca.xpertproject.apps.businessmanager.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;


public class MemberUtils {
	

	
	public boolean checkCookieMember(String loggedMember, MemberRepository memberRepository, Model model){
		
		boolean hasAccess=false;
		
		if(loggedMember!=null && !loggedMember.isEmpty() && !"guest".equals(loggedMember)) {
			
			List<Member> memberOpt = memberRepository.findByEmail(loggedMember);
			
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
			
			List<Member> memberOpt = memberRepository.findByEmail(loggedMember);
			
			if(!memberOpt.isEmpty()) {
				
				Member member = memberOpt.get(0);
				if(member.getActive())
				hasAccess = true;
			}
		}
		
		return hasAccess;
	}

}

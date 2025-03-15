package ca.xpertproject.apps.businessmanager.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import ca.xpertproject.apps.businessmanager.model.Member;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;


public class MemberUtils {
	

	
	public boolean checkCookieMember(String loggedMember, MemberRepository memberRepository, Model model) {
		
		boolean hasAccess=false;
		System.out.println("Checking member cooky present");
		if(loggedMember!=null && !loggedMember.isEmpty() && !"guest".equals(loggedMember)) {
			System.out.println("Member cooki=y value : " + loggedMember);
			List<Member> memberOpt = memberRepository.findByEmail(loggedMember);
			System.out.println("Member found : " +memberOpt.size());
			if(!memberOpt.isEmpty()) {
				System.out.println("Access granted for " + loggedMember);
				Member member = memberOpt.get(0);
				model.addAttribute("memberName", member.getFirstname() +  " " + member.getLastname());
				hasAccess = true;
			}
		}
		
		return hasAccess;
	}
	
public boolean checkCookieMember(String loggedMember, MemberRepository memberRepository) {
		
		boolean hasAccess=false;
		System.out.println("Checking member cooky present");
		if(loggedMember!=null && !loggedMember.isEmpty() && !"guest".equals(loggedMember)) {
			System.out.println("Member cooki=y value : " + loggedMember);
			List<Member> memberOpt = memberRepository.findByEmail(loggedMember);
			System.out.println("Member found : " +memberOpt.size());
			if(!memberOpt.isEmpty()) {
				System.out.println("Access granted for " + loggedMember);
				Member member = memberOpt.get(0);
				hasAccess = true;
			}
		}
		
		return hasAccess;
	}

}

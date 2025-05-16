package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.xpertproject.apps.businessmanager.model.ArticleRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.objects.ACObject;
import ca.xpertproject.apps.businessmanager.objects.mappers.ArticleMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ArticleRestController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	MemberUtils memberUtils = new MemberUtils();
	
	@GetMapping("getArticleList")
	public List<ACObject> getCustomerList(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, HttpServletRequest httpRequest){

		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository))return Collections.emptyList();

		List<ACObject> articleList = (List<ACObject>) articleRepository.findAll().stream().map(ArticleMapper::convertForList).collect(Collectors.toList());

		return articleList;
	}

}

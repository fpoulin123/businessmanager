package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Article;
import ca.xpertproject.apps.businessmanager.model.ArticleRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	MemberRepository memberRepository;

	MemberUtils memberUtils = new MemberUtils();
	
	@GetMapping("/articles")
	public String getArticles(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false, defaultValue = "1") String page, Model model, HttpServletRequest httpRequest) {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		
		List<Article> articleList = articleRepository.findAll();
		
		model.addAttribute("articles", articleList );
		
		return "articles";
	}

}

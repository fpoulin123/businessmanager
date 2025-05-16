package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Article;
import ca.xpertproject.apps.businessmanager.model.ArticleRepository;
import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.model.Payment;
import ca.xpertproject.apps.businessmanager.model.Purchase;
import ca.xpertproject.apps.businessmanager.model.PurchaseRepository;
import ca.xpertproject.apps.businessmanager.model.Subscription;
import ca.xpertproject.apps.businessmanager.objects.PurchaseExt;
import ca.xpertproject.apps.businessmanager.objects.mappers.PurchaseMapper;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import ca.xpertproject.apps.businessmanager.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SalesController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
	
	@GetMapping("createArticle")
	public String createArticle(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, Model model, HttpServletRequest httpRequest) {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		return "createArticleForm";
	}
	
	@PostMapping("createArticle")
	public String creteArticle(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model, HttpServletRequest httpRequest) throws ParseException {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Article article = new Article();
		
		article.setName(body.get("name"));
		
		article.setDescription(body.get("description"));
		
		Double price = null;
		
		String priceStr = body.get("price");
		if(StringUtils.exists(priceStr))
			price = Double.parseDouble(priceStr);
		
		article.setPrice(price);
		
		articleRepository.save(article);
		 
		return "redirect:articles";
	}
	
	
	@GetMapping("/sales")
	public String getPurchases(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false, defaultValue = "1") String page, Model model, HttpServletRequest httpRequest) {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		
		List<Purchase> purchaseList = purchaseRepository.findAll();
		
		List<PurchaseExt> purchaseExtList = purchaseList.stream().map(PurchaseMapper::convert).collect(Collectors.toList());
		
		model.addAttribute("purchases", purchaseExtList );
		
		return "sales";
	}
	
	
	@GetMapping("addSale")
	public String addSale(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam(required = false, defaultValue = "1") String page, Model model, HttpServletRequest httpRequest) {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		return "createSaleForm";
	}
	
	@PostMapping("addSale")
	public String addSale(@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, @RequestParam Map<String, String> body, HttpServletResponse response, Model model, HttpServletRequest httpRequest) throws ParseException {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))return "noaccess";
		
		Purchase purchase = new Purchase();
		
		String purchaseDateValue = body.get("purchaseDate");
		Date purchaseDate = null;
		
		if(purchaseDateValue!=null &&  !(purchaseDateValue.isEmpty())) {
			purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDateValue);
		}
				
		purchase.setPurchaseDate(purchaseDate);
		
		Long articleId = null;
		String articleIdStr = body.get("articleId");
		articleId = Long.parseLong(articleIdStr);
		
		Article article = articleRepository.findById(articleId);
		
		purchase.setArticle(article);
		
		Long customerId = null;
		String customerIdStr = body.get("customerId");
		if(StringUtils.exists(customerIdStr))
			customerId = Long.parseLong(customerIdStr);
		
		Customer customer = customerRepository.findById(customerId);
		
		purchase.setCustomer(customer);
		
		Double discount = 0.0;
		String discountStr = body.get("discount");
		if(StringUtils.exists(discountStr))
			discount = Double.parseDouble(discountStr);
				
		purchase.setDiscount(discount);
		
		Integer number = null;
		String numberStr = body.get("number");
		if(StringUtils.exists(numberStr))
			number = Integer.parseInt(numberStr);
		
		purchase.setNumber(number);
		
		
		purchaseRepository.save(purchase);
		 
		return "redirect:sales";
	}


}

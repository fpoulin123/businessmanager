package ca.xpertproject.apps.businessmanager.controller;

import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.MEMBER_LOGGED_COOKIE_NAME;
import static ca.xpertproject.apps.businessmanager.constant.ApplicationConstants.PICTURES_DIR_PATH;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ca.xpertproject.apps.businessmanager.exception.AuthenticationException;
import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerIdComparator;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
import ca.xpertproject.apps.businessmanager.model.MemberRepository;
import ca.xpertproject.apps.businessmanager.utils.MemberUtils;
import ca.xpertproject.apps.businessmanager.utils.PageUtils;
import ca.xpertproject.apps.businessmanager.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	MemberRepository memberRepository;

	MemberUtils memberUtils = new MemberUtils();

	@GetMapping("/allCustomers")
	public String getCustomers(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false, defaultValue = "1") String page, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model)) {
			
			return "noaccess";
		}

		List<Customer> customers = customerRepository.findAll();

		Comparator<Object> idComp = new CustomerIdComparator();
		Collections.sort(customers, idComp);

		PageUtils.getPagedItems(customers, model, page, "customers");

		return "customers";

	}

	@GetMapping("/customers")
	public String getCustomersByName(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = false) String name, @RequestParam(required = false) String firstname, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		List<Customer> customers = new ArrayList<Customer>();
		if (name != null && !name.isEmpty()) {
			customers = customerRepository.findByName(name);
		}

		if (firstname != null && !firstname.isEmpty()) {
			customers = customerRepository.findByFirstName(firstname);
		}

		Comparator<Object> idComp = new CustomerIdComparator();
		Collections.sort(customers, idComp);

		model.addAttribute("customers", customers);

		return "customers";

	}

	@GetMapping("/customer")
	public String getCustomer(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = true) Long id, Model model, HttpServletRequest httpRequest) throws AuthenticationException {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		Customer customer = customerRepository.findById(id);
		
		System.out.println("Picture path : " + customer.picture);

		model.addAttribute("customer", customer);

		return "customer";
	}

	@GetMapping("/editCustomer")
	public String getModifyCustomer(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam(required = true) Long id, Model model, HttpServletRequest httpRequest) throws AuthenticationException {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		Customer customer = customerRepository.findById(id);

		model.addAttribute("customer", customer);

		return "editCustomerForm";
	}

	@GetMapping("/createCustomer")
	public String getCreateCustomer(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember, Model model, HttpServletRequest httpRequest)
			throws AuthenticationException {
		
		if(!SecurityUtils.checkAuthorizedHost(httpRequest))return "noaccess";
		
		if (!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		return "createCustomerform";
	}

	@PostMapping(value = "/createCustomer")
	public String createCustomer(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam MultipartFile picture, @RequestParam Map<String, String> body, HttpServletResponse response,
			Model model, HttpServletRequest httpRequest) throws ParseException, AuthenticationException, IllegalStateException, IOException {
		
		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";

		String fileName = null;
		
		if(picture!=null&&!picture.isEmpty()) {
			String origName = picture.getOriginalFilename();

			String fileExt = origName.substring(origName.lastIndexOf("."), origName.length());

			String pictureFolder = PICTURES_DIR_PATH;
			
			File picturesDir= new File(PICTURES_DIR_PATH);
			if(!picturesDir.exists()) {
				picturesDir.mkdirs();
			}
			
			System.out.println("PICTURES: " + picturesDir.getAbsolutePath());
			
			fileName = UUID.randomUUID().toString() + fileExt;

			String destFilePath = pictureFolder + "/" + fileName;

			picture.transferTo(new File(destFilePath));

		}

		String heightValue = body.get("height");
		
		Integer height = (heightValue!=null&&!heightValue.isEmpty())?Integer.parseInt(heightValue):null;
		
		String weightValue = body.get("weight");
		
		Double weight = (weightValue!=null&&!weightValue.isEmpty())?Double.parseDouble(weightValue):null;
		
		String birthdateValue = body.get("birthdate");
		Date birthDate = null;

		if(birthdateValue!=null &&  !(birthdateValue.isEmpty())) {
			birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdateValue);
		}
		
		Customer customer = GenericBuilder.of(Customer::new).with(Customer::setFirstName, body.get("firstname"))
				.with(Customer::setLastName, body.get("lastname")).with(Customer::setAddress, body.get("address"))
				.with(Customer::setCity, body.get("city")).with(Customer::setPhoneNumber, body.get("phonenumber"))
				.with(Customer::setEmail, body.get("email"))
				.with(Customer::setTitle, body.get("title"))
				.with(Customer::setLevel, body.get("level"))
				.with(Customer::setHeight, height)
				.with(Customer::setWeight, weight)
				.with(Customer::setBirthdate, birthDate)
				.with(Customer::setPicture, fileName!=null?"./img/customerPictures/" + fileName:null).build();

		Customer newCustomer = customerRepository.save(customer);
		
		String barcodeValue = newCustomer.getBarcodeValue();
		if (barcodeValue == null || barcodeValue.isEmpty()) {
			String left = new SimpleDateFormat("yyyyMMdd").format(new Date());
			Long leftLong = Long.parseLong(left);
			Long barcodeValueLong = leftLong * 10000;
			barcodeValueLong = barcodeValueLong + newCustomer.getId();
			barcodeValue = barcodeValueLong.toString();
		}

		newCustomer.setBarcodeValue(barcodeValue);
		
		customerRepository.save(newCustomer);

		return "redirect:./customer?id=" + newCustomer.id;
	}

	@PostMapping(value = "/updateCustomer")
	public String updateCustomer(
			@CookieValue(value = MEMBER_LOGGED_COOKIE_NAME, defaultValue = "guest") String loggedMember,
			@RequestParam MultipartFile picture, @RequestParam Map<String, String> body, HttpServletResponse response, Model model, HttpServletRequest httpRequest)
			throws ParseException, AuthenticationException, IllegalStateException, IOException {

		if (!SecurityUtils.checkAuthorizedHost(httpRequest)||!memberUtils.checkCookieMember(loggedMember, memberRepository, model))
			return "noaccess";
		
		String fileName = null;
		
		if(picture!=null&&!picture.isEmpty()) {
			String origName = picture.getOriginalFilename();

			String fileExt = origName.substring(origName.lastIndexOf("."), origName.length());

			String pictureFolder =PICTURES_DIR_PATH;
			File picturesDir= new File(PICTURES_DIR_PATH);
			if(!picturesDir.exists()) {
				picturesDir.mkdirs();
			}
			System.out.println("PICTURES: " + picturesDir.getAbsolutePath());
			fileName = UUID.randomUUID().toString() + fileExt;

			String destFilePath = pictureFolder + "/" + fileName;

			picture.transferTo(new File(destFilePath));

		}
		
		Customer customer = customerRepository.findById(Long.parseLong(body.get("id")));
		
		String barcodeValue = customer.getBarcodeValue();
		if (barcodeValue == null || barcodeValue.isEmpty()) {
			String left = new SimpleDateFormat("yyyyMMdd").format(new Date());
			Long leftLong = Long.parseLong(left);
			Long barcodeValueLong = leftLong * 10000;
			barcodeValueLong = barcodeValueLong + customer.getId();
			barcodeValue = barcodeValueLong.toString();
		}

		String heightValue = body.get("height");
		
		Integer height = (heightValue!=null&&!heightValue.isEmpty())?Integer.parseInt(heightValue):null;
		
		String weightValue = body.get("weight");
		
		Double weight = (weightValue!=null&&!weightValue.isEmpty())?Double.parseDouble(weightValue):null;

		String birthdateValue = body.get("birthdate");
		Date birthDate = null;
		
		if(birthdateValue!=null&&!birthdateValue.isEmpty()) {
			birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdateValue);
		}

		customer = GenericBuilder.of(Customer::new).with(Customer::setId, customer.getId())
				.with(Customer::setFirstName, body.get("firstname")).with(Customer::setLastName, body.get("lastname"))
				.with(Customer::setAddress, body.get("address")).with(Customer::setCity, body.get("city"))
				.with(Customer::setPhoneNumber, body.get("phonenumber")).with(Customer::setEmail, body.get("email"))
				.with(Customer::setTitle, body.get("title"))
				.with(Customer::setLevel, body.get("level"))
				.with(Customer::setHeight, height)
				.with(Customer::setWeight, weight)
				.with(Customer::setBirthdate, birthDate)
				.with(Customer::setBarcodeValue, barcodeValue)
				.with(Customer::setPicture, fileName!=null?"./img/customerPictures/" + fileName:null).build();

		customerRepository.save(customer);
		
		return "redirect:./customer?id=" + customer.id;
	}
}

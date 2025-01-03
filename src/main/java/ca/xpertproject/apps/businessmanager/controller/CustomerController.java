package ca.xpertproject.apps.businessmanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerIdComparator;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/allCustomers")
	public String getCustomers(Model model) {
		
		List<Customer> customers = customerRepository.findAll();
		
		Comparator<Object> idComp = new CustomerIdComparator();
		Collections.sort(customers, idComp);
		
		model.addAttribute("customers", customers);
		
		model.addAttribute("course", "");
		
		
		return "customers";
		
	}
	
	@GetMapping("/customer")
	public String getCustomer(@RequestParam(required = true) Long id, Model model) {
		
		Customer customer = customerRepository.findById(id);
		
		model.addAttribute("customer", customer);
		
		return "customer";
	}
	
	@GetMapping("/modifyCustomer")
	public String getModifyCustomer(@RequestParam(required = true) Long id, Model model) {
		
		Customer customer = customerRepository.findById(id);
				
		model.addAttribute("customer", customer);
		
		return "modifyCustomerform";
	}
	
	@GetMapping("/createCustomer")
	public String getCreateCustomer() {
	
		return "createCustomerform";
	}
	
	@PostMapping(value="/createCustomer")
	public String createCustomer(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		String inscriptionDateValue = body.get("inscriptiondate");
		Date inscriptionDate = null;
		
		if(inscriptionDateValue!=null &&  !(inscriptionDateValue.isEmpty())) {
			inscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("inscriptiondate"));
		}
		
		Customer customer = Customer.builder()
				.firstName(body.get("firstname"))
				.lastName(body.get("lastname"))
				.address(body.get("address"))
				.city(body.get("city"))
				.phoneNumber(body.get("phonenumber"))
				.email(body.get("email"))
				.course(body.get("course"))
				.picture(body.get("picture"))
				.barcodeValue(body.get("barcodeValue"))
				.inscriptionDate(inscriptionDate).build();
				
				
				
				customerRepository.save(customer);
		
		
		return "redirect:./customer?id=" + customer.id;
	}
	
	@PostMapping(value="/updateCustomer")
	public String updateeCustomer(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		Iterator<Entry<String, String>> mapItr = body.entrySet().iterator();
		while (mapItr.hasNext()) {
			Map.Entry<java.lang.String, java.lang.String> entry = (Map.Entry<java.lang.String, java.lang.String>) mapItr
					.next();
			
			System.out.println(entry.getKey() + ":" + entry.getValue());
			
		}
		
		String inscriptionDateValue = body.get("inscriptiondate");
		Date inscriptionDate = null;
		
		if(inscriptionDateValue!=null &&  !(inscriptionDateValue.isEmpty())) {
			inscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("inscriptiondate"));
		}
		
		Customer customer = customerRepository.findById(Long.parseLong(body.get("id")));
		
		String barcodeValueLeft = inscriptionDateValue.replace("-","");
		Long barcodeValueLong = Long.parseLong(barcodeValueLeft)*1000000;
		barcodeValueLong = barcodeValueLong + customer.getId();
			
		customer = Customer.builder()
				.id(customer.id)
				.firstName(body.get("firstname"))
				.lastName(body.get("lastname"))
				.address(body.get("address"))
				.city(body.get("city"))
				.course(customer.getCourse())
				.barcodeValue(barcodeValueLong.toString())
				.phoneNumber(body.get("phonenumber"))
				.email(body.get("email"))
				.inscriptionDate(inscriptionDate).build();
		
				customerRepository.save(customer);
		
		return "redirect:./customer?id=" + customer.id;
	}
}	


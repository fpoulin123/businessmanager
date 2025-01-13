package ca.xpertproject.apps.businessmanager.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerIdComparator;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import ca.xpertproject.apps.businessmanager.model.GenericBuilder;
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
		
		return "customers";
		
	}
	
	@GetMapping("/customers")
	public String getCustomersByName(@RequestParam(required = false) String name, @RequestParam(required = false) String firstname, Model model) {
		List<Customer> customers = new ArrayList<Customer>();
		if(name!=null&&!name.isEmpty()) {
			customers = customerRepository.findByName(name);
		}
		
		if(firstname!=null&&!firstname.isEmpty()) {
			customers = customerRepository.findByFirstName(firstname);
		}
		
		
		Comparator<Object> idComp = new CustomerIdComparator();
		Collections.sort(customers, idComp);
		
		model.addAttribute("customers", customers);
		
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
		
		Customer customer = GenericBuilder.of(Customer::new)
				.with(Customer::setFirstName, body.get("firstname"))
				.with(Customer::setLastName,body.get("lastname"))
				.with(Customer::setAddress,body.get("address"))
				.with(Customer::setCity,body.get("city"))
				.with(Customer::setPhoneNumber,body.get("phonenumber"))
				.with(Customer::setEmail,body.get("email"))
				.with(Customer::setPicture,body.get("picture"))
				.build();

				customerRepository.save(customer);
		
		
		return "redirect:./customer?id=" + customer.id;
	}
	
	@PostMapping(value="/updateCustomer")
	public String updateCustomer(@RequestParam Map<String, String> body, HttpServletResponse response) throws ParseException {
		
		String inscriptionDateValue = body.get("inscriptiondate");
		Date inscriptionDate = null;
		
		if(inscriptionDateValue!=null &&  !(inscriptionDateValue.isEmpty())) {
			inscriptionDate = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("inscriptiondate"));
		}
		
		Customer customer = customerRepository.findById(Long.parseLong(body.get("id")));
		
		String barcodeValue = customer.getBarcodeValue();
		if(barcodeValue==null||barcodeValue.isEmpty()) {
			String left = new SimpleDateFormat("yyyyMMdd").format(new Date());
			Long leftLong = Long.parseLong(left);
			Long barcodeValueLong = leftLong * 1000000;
			barcodeValueLong = barcodeValueLong + customer.getId();
			barcodeValue = barcodeValueLong.toString();			
		}
		
		customer = GenericBuilder.of(Customer::new)
				.with(Customer::setFirstName, body.get("firstname"))
				.with(Customer::setLastName,body.get("lastname"))
				.with(Customer::setAddress,body.get("address"))
				.with(Customer::setCity,body.get("city"))
				.with(Customer::setPhoneNumber,body.get("phonenumber"))
				.with(Customer::setEmail,body.get("email"))
				.build();
		
				customerRepository.save(customer);
		
		return "redirect:./customer?id=" + customer.id;
	}
}	


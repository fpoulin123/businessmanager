package ca.xpertproject.apps.businessmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/allCustomers")
	public String getCustomers(Model model) {
		
		List<Customer> customers = customerRepository.findAll();
		
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
	
	@PostMapping(value="/createCustomer", consumes = "application/json")
	public String createCustomer(@RequestBody Customer newCustomer, HttpServletRequest request) {
		
		Customer customer = customerRepository.save(newCustomer);
		
		
		return "redirect:./customer?id=" + customer.id;
	}
}	

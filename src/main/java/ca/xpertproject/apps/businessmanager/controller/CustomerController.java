package ca.xpertproject.apps.businessmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;

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
	
	

}

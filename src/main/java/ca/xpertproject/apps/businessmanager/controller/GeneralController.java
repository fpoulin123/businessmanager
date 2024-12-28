package ca.xpertproject.apps.businessmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.xpertproject.apps.businessmanager.model.Customer;
import ca.xpertproject.apps.businessmanager.model.CustomerRepository;


@Controller
public class GeneralController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/home")
	public String getEvent() {
				
		return "home";
		
	}
	
	
	

}

package ca.xpertproject.apps.businessmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("testAC")
	public String testAutoComplete(Model model) {
		
		
		return "autocomplete";
	}

}

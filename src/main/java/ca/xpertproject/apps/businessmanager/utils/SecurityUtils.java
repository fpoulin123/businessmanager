package ca.xpertproject.apps.businessmanager.utils;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {
	private static final List<String> AUTHORIZED_HOST = Arrays.asList(
			"http://localhost/",
			"http://localhost:8080/mybusiness", 
			"https://clubdefenseexpert.com/mybusiness", 
			"http://clubdefenseexpert.com/mybusiness");
	
	public static boolean checkAuthorizedHost(HttpServletRequest request) {
		
		boolean hasAccess = false;
		
		String url = request.getRequestURL().toString();
		
		for(String authHost:AUTHORIZED_HOST) {
//			System.out.println("URL : " + url + ", compare to " +authHost); 
			if(url.startsWith(authHost))hasAccess=true;
		}
		
		return hasAccess;
	}

}



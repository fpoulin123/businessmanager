package ca.xpertproject.apps.businessmanager.utils;

public class StringUtils {
	
	
	public static boolean exists(String string) {
		
		return(string!=null&&!string.isEmpty()&&!string.isBlank());
	}

}

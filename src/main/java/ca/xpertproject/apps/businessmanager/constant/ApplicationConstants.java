package ca.xpertproject.apps.businessmanager.constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConstants {
	
	public static Properties myBusinessProps = getGlobalProperties();
	
	public static final String MEMBER_LOGGED_COOKIE_NAME = "mybusinessLoggedMember";
	
	public static final String PICTURES_DIR_PATH = myBusinessProps.getProperty("pictures.dir.path");

	private static Properties getGlobalProperties(){
		Properties properties = new Properties();
		try {
			
			File propFile = new File("./global.properties");
			
			if(!propFile.exists())propFile.createNewFile();
			
			InputStream propsIn = new FileInputStream(propFile);
			properties.load(propsIn);
			
			propsIn.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
			
}

package ca.xpertproject.apps.businessmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBusinessApplication {

	public static Properties myBusinessProps = getGlobalProperties();
	
	public static void main(String[] args) {
		SpringApplication.run(MyBusinessApplication.class, args);
		
		 
	}
	
	private static Properties getGlobalProperties(){
		Properties properties = new Properties();
		try {
			
			
			File propFile = new File("../global.properties");
			System.out.println("Properties file path : " + propFile.getAbsolutePath());
			if(!propFile.exists())propFile.createNewFile();
			
			InputStream propsIn = new FileInputStream(propFile);
			properties.load(propsIn);
			
			propsIn.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	@Bean
	@ConfigurationProperties("app.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}

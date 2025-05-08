package ca.xpertproject.apps.businessmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

class SecurityConfig implements WebMvcConfigurer{
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
				requests -> requests
				.requestMatchers("/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				);

		return http.build();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*");
    }

}

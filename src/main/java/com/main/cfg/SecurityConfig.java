package com.main.cfg;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 //InMemory DB as the authentication info provider
		 auth.inMemoryAuthentication().withUser("sukanta").password("{noop}chintu@1995").roles("CUSTOMER");
		 auth.inMemoryAuthentication().withUser("manoj").password("{noop}muna@1995").roles("MANAGER");
		 auth.inMemoryAuthentication().withUser("mukti").password("{noop}mukti@1995").roles("CUSTOMER","MANAGER");
		 auth.inMemoryAuthentication().withUser("raja").password("{noop}raja@1995").roles("VISITOR");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//place the authentication and authorization logics for the request url
		http.authorizeRequests().antMatchers("/").permitAll()//no Authentication and Authorization 
		.antMatchers("/offers").authenticated()//only Authentication
		.antMatchers("/showBalance").hasAnyRole("CUSTOMER","MANAGER")
		.antMatchers("/approveLoan").hasRole("MANAGER")
		.anyRequest().authenticated()
		.and().httpBasic();
		 
	}
	
}

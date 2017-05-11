package com.example.oAuth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class ConsectusWebSecurity extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private UserDetailsService userDetailsService;

    @Bean
    public PlaintextPasswordEncoder passwordEncoder() {
        return new PlaintextPasswordEncoder();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/swagger-ui.html","/webjars/**","/swagger-resources/**", "/configuration/ui", "/configuration/ui/**", "/v2/api-docs/**").permitAll()
        .anyRequest().authenticated()
//        .and().httpBasic()
        .and().csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/resources/**"); // #3
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		//using JDBC store for validating
    		auth.userDetailsService(userDetailsService)
    		.passwordEncoder(passwordEncoder());
    	}
    
	 @Bean
	 @Override
	 public UserDetailsService userDetailsServiceBean() throws Exception {
		 return super.userDetailsServiceBean();
	 }
	 
    		  

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

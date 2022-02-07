package com.bg.ticketwebshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bg.ticketwebshop.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
    @Autowired
    public void setCustomAuthenticationSuccessHandler(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
    	this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }
    
    /**/
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
    	auth.userDetailsService(userService);
    }
	
	// add a reference to our security data source
    @Autowired
    private UserService userService;
    
	/**
	 * In memory authentication, only test purposes. Not recommended for production.
	 */
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("user123").roles("USER"))
			.withUser(users.username("mary").password("user123").roles("PROVIDER", "USER"))
			.withUser(users.username("tom").password("user123").roles("ADMIN", "USER"))
			.withUser(users.username("geza").password("user123").roles("ADMIN","USER","PROVIDER"));
	}*/
	
	/**
	 * Own login-page, with permit all to allow everybody to reach the login site.
	 * LoginPage-Controller has the path: /showLoginPage
	 * http.authorizeHttpRequests() -> login has been limited according HttpServletRequest
	 * .anyRequest().authenticated() -> everybody needs authentication
	 * .loginPage("/showLoginPage") -> have to be a controller with this GetMethod
	 * .loginProcessingUrl("/authenticateUser") -> this it the path what shows the way to the
	 * 												Controller method what will do the authentication
	 * 												this have to be a POST method
	 * .permitAll() -> everybody has access to the login-site
	 * 
	 * .and().logout().permitAll(); -> to the logout everybody has access.
	 * 
	 * .and().exceptionHandling().accessDeniedPage("/exception/access-denied") -> exception handling
	 */
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				//.anyRequest().authenticated()
			.antMatchers("/").hasRole("USER")
			//.antMatchers("/").permitAll()
			.antMatchers("/users/**").hasRole("USER")
			.antMatchers("/provider/**").hasRole("PROVIDER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
				.formLogin()
					.loginPage("/showLoginPage")
					.loginProcessingUrl("/authenticateUser")
					.successHandler(customAuthenticationSuccessHandler)
					.permitAll()
			.and()
				.logout().permitAll()
			.and().exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		
		/**
		 * Set up new DaoAuthenticationProvider (auth properties)
		 */
		
		auth.setUserDetailsService(userService);
		
		auth.setPasswordEncoder(passwordEncoder());
		
		return auth;
	}

}

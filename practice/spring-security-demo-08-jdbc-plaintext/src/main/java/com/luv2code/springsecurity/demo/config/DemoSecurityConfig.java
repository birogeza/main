package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;/*�j*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// kell egy referencia a securityDataSource-unkhoz:
	@Autowired
	private DataSource securityDataSource;
	
	// m�r nincs sz�ks�g�nk az inMemory autentik�ci�s adatokra, �gy azokat t�rl�lj�k ki:
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		// haszn�ljuk a jdbc autentik�ci�t, ami megmondja az Spring Security-nek, hogy
		// a jdbc-t haszn�lja hardk�dolt userek �s role-ok helyett:
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			//	.anyRequest().authenticated()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasAnyRole("MANAGER","ADMIN")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
			
	}
}

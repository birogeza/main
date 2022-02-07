package com.lotterygame.secu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("geza")
                .password("{noop}geza")
                .roles("ADMIN","USER")
                .and()
                .withUser("user")
                .password("{noop}user")
                .roles("USER");
    }

    public void configure(HttpSecurity httpSec) throws Exception{
        httpSec
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/")
                    .permitAll()
                .antMatchers("/sorsolas.html")
                    .hasRole("USER")
                .antMatchers("/main.html")
                    .hasRole("USER")
                .antMatchers("/huzasok.html")
                    .hasRole("USER")
                .antMatchers("regisztracio.html")
                    .permitAll()
                .antMatchers("index.html")
                    .permitAll()
                .antMatchers("/db/**").permitAll()
                //this below section needs to be able to log in to H2 Console
                .and()
                    .authorizeRequests().antMatchers("/console/**")
                        .permitAll()


                .and()
                .formLogin()
                .permitAll();

        httpSec.csrf().disable();
        httpSec.headers().frameOptions().disable();
    }
}

package com.lotterygame.secu;

import com.lotterygame.service.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Qualifier("userServiceImplementation")
    @Autowired
    private UserDetailsService userService;


    /**
     * A password kódolást egy egyszerű kódolatlan módszerrel helyettesítem.
     * a rawpassword-ot Stringgé alakítva adom vissza.
     */
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return String.valueOf(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        });
    }

    public void configure(HttpSecurity httpSec) throws Exception{
        httpSec
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/")
                    .permitAll()
                .antMatchers("/sorsolas.html")
                    //nincsenek role-ok, csak az a fontos, hogy authentikálja magát a user, ezért nem hasRole()-t használok.
                    .authenticated()
                .antMatchers("/main.html")
                    .authenticated()
                    //.hasRole("USER")
                .antMatchers("/huzasok.html")
                    .authenticated()
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
                    .permitAll().
                and()
                    .csrf().disable();

        httpSec.headers().frameOptions().disable();
    }


}

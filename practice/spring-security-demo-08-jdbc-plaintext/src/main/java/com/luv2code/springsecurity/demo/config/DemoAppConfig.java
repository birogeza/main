package com.luv2code.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	//�ll�tsunk be egy �j v�ltoz�t, hogy a property adatait t�rolja
	@Autowired
	private Environment env;
	
	//set up a logger for diagnostics:
	private Logger logger = Logger.getLogger(getClass().getName());	
	
	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	//�ll�sunk be egy @Bean-t a security adatb�zisunkhoz:
	
	@Bean
	public DataSource securityDataSource() {
		
		// �ll�tsuk be a connection pool-t
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		
		//�ll�tsuk be a JDBC driver oszt�lyt
		
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		
		// logoljuk a kapcsolat st�tusz�t, biztons�gi okokb�l
		logger.info("\n>>>> jdbc.url= " + env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user= " + env.getProperty("jdbc.user"));
		
		
		// �ll�tsuk be az adatb�zis tulajdons�gokat
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		//�ll�tsuk be a connection pool tulajdons�gokat:
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// hozzunk l�tre egy seg�t� met�dust, ami kiolvassa a k�rnyezeti propertyb�l az eredm�nyt �s
	// az �rt�ket �s int-t� alak�tva adja vissza
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}	
}










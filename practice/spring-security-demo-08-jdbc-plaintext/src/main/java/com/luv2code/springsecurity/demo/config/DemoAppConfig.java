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

	//állítsunk be egy új változót, hogy a property adatait tárolja
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
	
	//állísunk be egy @Bean-t a security adatbázisunkhoz:
	
	@Bean
	public DataSource securityDataSource() {
		
		// állítsuk be a connection pool-t
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		
		//állítsuk be a JDBC driver osztályt
		
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		
		// logoljuk a kapcsolat státuszát, biztonsági okokból
		logger.info("\n>>>> jdbc.url= " + env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user= " + env.getProperty("jdbc.user"));
		
		
		// állítsuk be az adatbázis tulajdonságokat
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		//állítsuk be a connection pool tulajdonságokat:
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// hozzunk létre egy segítõ metódust, ami kiolvassa a környezeti propertybõl az eredményt és
	// az értéket és int-té alakítva adja vissza
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}	
}










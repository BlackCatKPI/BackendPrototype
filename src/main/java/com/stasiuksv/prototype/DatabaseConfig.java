package com.stasiuksv.prototype;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfig 
{

	@Bean
  	public DataSource dataSource() 
  	{
  
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(env.getProperty("db.driver"));
    	dataSource.setUrl(env.getProperty("db.url"));
    	dataSource.setUsername(env.getProperty("db.username"));
    	dataSource.setPassword(env.getProperty("db.password"));
    	return dataSource;
  	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() 
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.stasiuksv.prototype.model" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	 
	private Properties getHibernateProperties() 
	{
	
	    Properties additionalProperties = new Properties();
	    additionalProperties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
	    additionalProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
	    additionalProperties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
	  
		return additionalProperties;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() 
	{
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
  
  
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() 
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	  // Private fields
	  
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	  
	@Autowired
	private SessionFactory sessionFactory;

}
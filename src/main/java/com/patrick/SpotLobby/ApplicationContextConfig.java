package com.patrick.SpotLobby;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.patrick.SpotLobby.Beans"})
@EnableJpaRepositories(
		 entityManagerFactoryRef = "postgresEntityManagerFactory", 
	     transactionManagerRef = "postgresTransactionManager",
	     basePackages = {"com.patrick.SpotLobby.DAOManagers"})
public class ApplicationContextConfig {
	
	@Bean(name = "postgresEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.patrick.SpotLobby.Beans");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "none");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		factory.setJpaProperties(jpaProperties);
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean(name = "postgresDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(System.getenv("POSTGRES_DRIVER"));
		dataSource.setUrl("SPOTLOBBY_DB_URL");
		dataSource.setUsername("SPOTLOBBY_DB_USER");
		dataSource.setPassword("SPOTLOBBY_DB_PASS");
		return dataSource;
	}

	@Bean(name = "postgresTransactionManager")
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public org.springframework.orm.hibernate5.HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new org.springframework.orm.hibernate5.HibernateExceptionTranslator();
	}

}

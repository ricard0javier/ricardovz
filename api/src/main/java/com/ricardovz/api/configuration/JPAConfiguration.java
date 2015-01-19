package com.ricardovz.api.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Set the JPA behaviour and define the datasources
 * 
 * @author Ricardo Javier
 *
 */
@Configuration
@EnableJpaRepositories("com.ricardovz.api")
@EnableTransactionManagement
@Slf4j
public class JPAConfiguration {

    /**
     * Defines the datasource to be used by the application
     * 
     * @param className
     * @param serverName
     * @param port
     * @param databaseName
     * @param user
     * @param password
     * @return DataSource
     */
    @Bean
    public DataSource dataSource(
	    @Value("${dataSourceClassName}") String className,
	    @Value("${dataSource.serverName}") String serverName,
	    @Value("${dataSource.port}") String port,
	    @Value("${dataSource.databaseName}") String databaseName,
	    @Value("${dataSource.user}") String user,
	    @Value("${dataSource.password}") String password) {

	log.debug("Defining the datasource to be used by the application based on the application.properties file");

	HikariDataSource datasource = new HikariDataSource();
	datasource.setDataSourceClassName(className);
	datasource.addDataSourceProperty("serverName", serverName);
	datasource.addDataSourceProperty("port", port);
	datasource.setCatalog(databaseName);
	datasource.setUsername(user);
	datasource.setPassword(password);

	return datasource;
    }

    /**
     * Set the JPA vendor, it will auto-generate the database schema based on
     * the domain classes
     * 
     * @param datasource
     * @return EntityManagerFactory
     */
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource datasource) {

	log.debug("Setting the JPA vendor, it will autogenerate the database schema based on the domain classes");	
	
	HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	vendorAdapter.setGenerateDdl(true);

	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	factory.setJpaVendorAdapter(vendorAdapter);
	factory.setPackagesToScan("com.ricardovz.api");
	factory.setDataSource(datasource);
	factory.afterPropertiesSet();

	return factory.getObject();
    }
    
    /**
     * Enables the transaction manager
     * 
     * @param entityManagerFactory
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

	log.debug("Enabling the transaction manager");
	
	JpaTransactionManager txManager = new JpaTransactionManager();
	txManager.setEntityManagerFactory(entityManagerFactory);
	return txManager;
    }
}

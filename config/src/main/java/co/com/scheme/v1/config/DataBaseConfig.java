package co.com.scheme.v1.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "co.com.scheme.v1.repository", entityManagerFactoryRef = "dataBaseEntityManagerFactory", transactionManagerRef = "dataBaseTransactionManager")
@EnableTransactionManagement
public class DataBaseConfig {

	    @Value("${person.database.driver}")
		private String driver;

		@Value("${person.database.url}")
		private String url;

		@Value("${person.database.username}")
		private String user;

		@Value("${person.database.password}")
		private String password;
		
		@Value("${person.database.dialect}")
		private String dialect;
		
		@Value("${person.database.show_sql}")
		private String show_sql;
		
		@Bean
	    public DataSource DataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(driver);
	        dataSource.setUrl(url);
	        dataSource.setUsername(user);
	        dataSource.setPassword(password);
	        return dataSource;
	    }

	    @Bean(name = "dataBaseEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean dataBaseEntityManagerFactory() {
	        LocalContainerEntityManagerFactoryBean em
	                = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(DataSource());
	        em.setPackagesToScan("co.com.scheme.v1.model.entities");
	        em.setPersistenceUnitName("dataBaseEntityManagerFactory");

	        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        em.setJpaProperties(additionalProperties());
	        return em;
	    }

	    @Bean(name = "dataBaseTransactionManager")
	    public PlatformTransactionManager dataBaseTransactionManager(@Qualifier("dataBaseEntityManagerFactory") EntityManagerFactory emf) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(emf);
	        return transactionManager;
	    }

	    Properties additionalProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("hibernate.dialect", dialect);
	        properties.setProperty("hibernate.hbm2ddl.auto", "update");
	        properties.setProperty("hibernate.show_sql", show_sql);

	        return properties;
	    }
}
